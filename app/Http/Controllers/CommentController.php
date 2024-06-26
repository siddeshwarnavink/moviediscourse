<?php

namespace App\Http\Controllers;

use App\Neo4j\Driver;
use App\Models\Movie;
use App\Models\Comment;
use Illuminate\Http\Request;
use App\Http\Requests\CommentRequest;
use Illuminate\Support\Facades\Auth;

class CommentController extends Controller
{

    protected $neo4jDriver;

    public function __construct(Driver $neo4jDriver)
    {
        $this->neo4jDriver = $neo4jDriver;
    }

    private function getNestedComments($parentComment)
    {
        $nestedComments = [];
        $sortedChildComments = $parentComment->childComments->sortByDesc('created_at');

        foreach ($sortedChildComments as $comment) {
            array_push($nestedComments, [
                'id' => $comment->id,
                'user' => $comment->creator()->select(['id', 'name'])->first(),
                'commentText' => $comment->commentText,
                'comments' => $this->getNestedComments($comment)
            ]);
        }

        return $nestedComments;
    }

    public function getComments(Request $request, $movieId)
    {
        if (Movie::where('id', $movieId)->exists()) {
            $comments = [];

            $fetchedComments = Comment::where('movie', $movieId)
                ->whereNull('parent')
                ->with(['childComments' => function ($query) {
                    $query->orderBy('created_at', 'desc');
                }, 'creator'])
                ->orderBy('created_at', 'desc')
                ->get();
            foreach ($fetchedComments as $parentComment) {
                array_push($comments, [
                    'id' => $parentComment->id,
                    'user' => $parentComment->creator()->select(['id', 'name'])->first(),
                    'commentText' => $parentComment->commentText,
                    'rating' => $parentComment->rating,
                    'comments' => $this->getNestedComments($parentComment)
                ]);
            }
            return response()->json($comments);
        } else {
            return response()->json(['message' => 'Movie not found'], 404);
        }
    }

    public function postComments(CommentRequest $request, $movieId)
    {
        if (Movie::where('id', $movieId)->exists()) {
            if (!$request->get('parentId', null) && !$request->rating) {
                return response()->json(['message' => 'Rating is required'], 400);
            }

            $comment = Comment::create([
                'commentText' => $request->commentText,
                'parent' => $request->get('parentId', null),
                'creator' => auth()->id(),
                'movie' => $movieId,
                'rating' => $request->rating
            ]);

            if (!$request->get('parentId', null)) {
                $cypher = '
                    MERGE (m:Movie {id: $movieId})
                    MERGE (u:User {id: $userId})
                    CREATE (comment:Comment {id: $commentId, rating: $rating})
                    MERGE (comment)-[:COMMENTED_ON]->(m)
                    MERGE (comment)-[:CREATED_BY]->(u)
                    RETURN comment
                ';

                $this->neo4jDriver->run($cypher, [
                    'commentId' => $comment->id,
                    'rating' => $request->rating,
                    'movieId' => $movieId,
                    'userId' => Auth::user()->id,
                ]);
            }

            return response()->json([
                'id' => $comment->id,
                'user' => $comment->creator()->select(['id', 'name'])->first(),
                'commentText' => $comment->commentText,
                'rating' => $comment->rating,
                'comments' => $this->getNestedComments($comment)
            ]);
        } else {
            return response()->json(['message' => 'Movie not found'], 404);
        }
    }

    public function updateComments(CommentRequest $request, $movieId, $commentId)
    {
        if (Comment::where('id', $commentId)->exists()) {
            Comment::where('id', $commentId)->update([
                'commentText' => $request->commentText,
            ]);
            return $this->getNestedComments(Comment::where('id', $commentId)->first());
        } else {
            return response()->json(['message' => 'Comment not found'], 404);
        }
    }

    public function deleteComments(Request $request, $movieId, $commentId)
    {
        $comment = Comment::find($commentId);
        if ($comment) {
            $this->deleteCommentAndChildren($comment);

            if(!$comment->parent) {
                $cypher = '
                    MATCH (comment:Comment {id: $commentId, rating: $rating})
                    OPTIONAL MATCH (comment)-[r]-()
                    DELETE comment, r
                ';

                $this->neo4jDriver->run($cypher, [
                    'commentId' => $comment->id,
                    'rating' => $comment->rating,
                ]);
            }

            return response()->json(['message' => 'Comment and its children deleted'], 204);
        } else {
            return response()->json(['message' => 'Comment not found'], 404);
        }
    }

    private function deleteCommentAndChildren($comment)
    {
        $children = Comment::where('parent', $comment->id)->get();
        foreach ($children as $child) {
            $this->deleteCommentAndChildren($child);
        }
        $comment->delete();
    }
}

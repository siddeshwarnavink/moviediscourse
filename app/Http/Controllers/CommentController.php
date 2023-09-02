<?php

namespace App\Http\Controllers;


use App\Models\User;
use App\Models\Movie;
use App\Models\Comment;
use Illuminate\Http\Request;

class CommentController extends Controller
{

    private function getNestedComments($parentComment) {
        $nestedComments = [];
    
        foreach ($parentComment->childComments as $comment) {
            array_push($nestedComments, [
                'id' => $comment->id,
                'user' => $comment->creator()->select(['id', 'name'])->first(),
                'commentText' => $comment->commentText,
                'comments' => $this->getNestedComments($comment)
            ]);
        }
    
        return $nestedComments;
    }

    public function getComments(Request $request, $movieId) {
        if(Movie::where('id', $movieId)->exists()) {
            $comments = [];

            $fetchedComments = Comment::where('movie', $movieId)
                ->whereNull('parent')
                ->with('childComments', 'creator')
                ->get();
            foreach ($fetchedComments as $parentComment) {
                array_push($comments, [
                    'id' => $parentComment->id,
                    'user' => $parentComment->creator()->select(['id', 'name'])->first(),
                    'commentText' => $parentComment->commentText,
                    'comments' => $this->getNestedComments($parentComment)
                ]);
            }
            return response()->json($comments);
        } else {
            return response()->json(['message' => 'Movie not found'], 404);
        }
    }
}
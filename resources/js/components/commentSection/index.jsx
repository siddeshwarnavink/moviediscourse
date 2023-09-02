import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';

import Comment from './comment';

function CommentSection() {
    const [comments, setComments] = useState([
        {
            id: 2342023232132,
            user: { id: '652023232132', name: 'John doe' },
            commentText: 'This is good',
            comments: [
                {
                    id: 2023232132,
                    editing: false,
                    user: { id: '1', name: 'Sid' },
                    commentText: 'Yeah!!',
                    comments: []
                },
                {
                    id: 3652023232132,
                    user: { id: '652023232132', name: 'John doe' },
                    commentText: 'I too think the same',
                    comments: []
                }
            ]
        }
    ]);

    const updateComment = (id, newCommentText) => {
        const updateRecursive = (comments) => {
            return comments.map(comment => {
                if (comment.id === id) {
                    return {
                        ...comment,
                        commentText: newCommentText,
                        editing: false,
                    };
                }
                if (comment.comments.length > 0) {
                    return {
                        ...comment,
                        comments: updateRecursive(comment.comments),
                    };
                }
                return comment;
            });
        };

        const newComments = updateRecursive(comments);
        setComments(newComments);
    };

    const setEditing = (id, updateEditing = true) => {
        const setRecursive = (comments) => {
            return comments.map(comment => {
                if (comment.id === id) {
                    return {
                        ...comment,
                        editing: updateEditing
                    };
                }
                if (comment.comments.length > 0) {
                    return {
                        ...comment,
                        comments: setRecursive(comment.comments)
                    };
                }
                return comment;
            });
        };

        const newComments = setRecursive(comments);
        setComments(newComments);
    };

    const addReply = (parentId) => {
        const newReply = {
            id: Date.now(),
            editing: true,
            user: { id: window.USER.id, name: window.USER.name },
            commentText: '',
            comments: []
        };

        const addRecursive = (comments) => {
            return comments.map(comment => {
                if (comment.id === parentId) {
                    return {
                        ...comment,
                        comments: [newReply, ...comment.comments]
                    };
                }
                if (comment.comments.length > 0) {
                    return {
                        ...comment,
                        comments: addRecursive(comment.comments)
                    };
                }
                return comment;
            });
        };

        const newComments = addRecursive(comments);
        setComments(newComments);
    };

    const deleteComment = (id) => {
        const deleteRecursive = (comments) => {
            return comments.filter(comment => {
                if (comment.id === id) {
                    return false;
                }
                if (comment.comments.length > 0) {
                    comment.comments = deleteRecursive(comment.comments);
                }
                return true;
            });
        };
    
        const newComments = deleteRecursive(comments);
        setComments(newComments);
    };    

    return (
        <div>
            <h5 className='my-2'>Reviews</h5>
            <ul className='comment-list'>
                {comments.map(comment => {
                    return (
                        <Comment
                            key={comment.id}
                            commentId={comment.id}
                            commentText={comment.commentText}
                            userName={comment.user.name}
                            userId={comment.user.id}
                            comments={comment.comments}
                            updateComment={updateComment}
                            setEditing={setEditing}
                            addReply={addReply}
                            deleteComment={deleteComment}
                        />
                    );
                })}
            </ul>
        </div>
    );
}

const container = document.getElementById('react-comments');
const root = createRoot(container);
root.render(<CommentSection />);

export default CommentSection;
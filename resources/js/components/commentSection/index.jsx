import React, { useEffect, useState } from 'react';
import { createRoot } from 'react-dom/client';
import axios from 'axios';

import Comment from './comment';
import CommentEditor from './commentEditor';

export const RATINGS = ['Very Bad', 'Bad', 'Moderate', 'Good', 'Very Good']

function CommentSection() {
    const [loading, setLoading] = useState(true);
    const [editText, setEditText] = useState('');
    const [rating, setRating] = useState(3);
    const [comments, setComments] = useState([]);
    useEffect(() => {
        fetchComments();
    }, []);

    const fetchComments = async () => {
        const response = await axios.get(`/api/movies/${window.MOVIE.id}/comments`);
        setLoading(false);
        if (response.status === 200) {
            setComments(response.data);
        }
    }

    const updateComment = (id, newCommentText) => {
        axios.patch(`/api/movies/${window.MOVIE.id}/comments/${id}`, {
            commentText: newCommentText
        });
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
                    if (!updateEditing && comment.commentText.trim() === '') {
                        deleteComment(id);
                        return null;
                    }
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
            }).filter(Boolean);
        };

        const newComments = setRecursive(comments);
        setComments(newComments);
    };

    const addReply = async (parentId) => {
        const response = await axios.post(`/api/movies/${window.MOVIE.id}/comments?parentId=${parentId}`, {
            commentText: null
        });
        const newReply = {
            id: response.data.id,
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
        axios.delete(`/api/movies/${window.MOVIE.id}/comments/${id}`);
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

    const handleCreate = async () => {
        if (editText.trim() !== '') {
            const response = await axios.post(`/api/movies/${window.MOVIE.id}/comments`, {
                commentText: editText,
                rating
            });
            const newReply = {
                id: response.data.id,
                editing: false,
                user: { id: window.USER.id, name: window.USER.name },
                commentText: editText,
                comments: []
            };
            setComments(prevComments => {
                const updatedComments = [newReply, ...prevComments];
                return updatedComments;
            })
            setEditText('');
        }
    }

    if (loading) {
        return (
            <div className='d-flex justify-content-center'>
                <div className='spinner-border' role='status'>
                    <span className='sr-only'>Loading...</span>
                </div>
            </div>
        );
    }

    return (
        <div>
            <h5 className='my-2'>Reviews</h5>
            {window.USER ? (
                <div className='mb-4'>
                    <div className='form-group'>
                        <textarea
                            name='commentText'
                            className='form-control'
                            rows={3}
                            placeholder='Post your comment'
                            value={editText}
                            onChange={(e) => setEditText(e.target.value)}
                        ></textarea>
                    </div>
                    <div className='form-group'>
                        <label class="form-label">Rating</label>
                        <select
                            name='rating'
                            className='form-control col-2'
                            value={rating}
                            onChange={event => setRating(parseInt(event.target.value))}>
                            {RATINGS.map((ratingLabel, index) => (
                                <option value={index + 1} key={ratingLabel}>
                                    {ratingLabel}
                                </option>
                            ))}
                        </select>
                    </div>
                    <button
                        className='btn btn-primary'
                        onClick={handleCreate}
                        type='button'
                    >
                        Post
                    </button>
                </div>
            ) : null}
            {comments.length > 0 ? (
                <ul className='comment-list'>
                    {comments.map(comment => {
                        if (comment.editing) {
                            return (
                                <CommentEditor
                                    key={comment.id}
                                    commentId={comment.id}
                                    updateComment={updateComment}
                                    commentText={comment.commentText}
                                    setEditing={setEditing}
                                />
                            );
                        }
                        return (
                            <Comment
                                key={comment.id}
                                commentId={comment.id}
                                commentText={comment.commentText}
                                rating={comment.rating}
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
            ) : <p>Be the first to post a comment!</p>}
        </div>
    );
}

const container = document.getElementById('react-comments');
const root = createRoot(container);
root.render(<CommentSection />);

export default CommentSection;
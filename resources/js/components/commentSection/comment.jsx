import React from 'react';

import CommentEditor from './commentEditor';

function Comment(props) {
    return (
        <li className='comment'>
            <div className='comment-user'>
                <img src='/assets/profile-picture.png' alt='User Avatar' className='comment-avatar' />
                <span className='comment-username'>{props.userName}</span>
            </div>
            <div className='comment-content'>
                <p>{props.commentText}</p>
                {window.USER ? (
                    <div className='comment-actions'>
                        <button
                            className='btn reply-button'
                            onClick={() => props.addReply(props.commentId)}
                        >
                            Reply
                        </button>
                        {props.userId === window.USER.id ? (
                            <>
                                <button
                                    className='btn reply-button'
                                    onClick={() => props.setEditing(props.commentId)}
                                >
                                    Edit
                                </button>

                                <button
                                    className='btn reply-button text-danger'
                                    onClick={() => props.deleteComment(props.commentId)}
                                >
                                    Delete
                                </button>
                            </>
                        ) : null}
                    </div>
                ) : null}
            </div>
            {props.comments && props.comments.length > 0 && (
                <ul className='comment-list'>
                    {props.comments.map(comment => {
                        if (comment.editing) {
                            return (
                                <CommentEditor
                                    key={comment.id}
                                    commentId={comment.id}
                                    updateComment={props.updateComment}
                                    commentText={comment.commentText}
                                    setEditing={props.setEditing}
                                />
                            )
                        }
                        return (
                            <Comment
                                key={comment.id}
                                commentId={comment.id}
                                commentText={comment.commentText}
                                userName={comment.user.name}
                                comments={comment.comments}
                                userId={comment.user.id}
                                updateComment={props.updateComment}
                                setEditing={props.setEditing}
                                addReply={props.addReply}
                                deleteComment={props.deleteComment}
                            />
                        );
                    })}
                </ul>
            )}
        </li>
    );
}

export default Comment;

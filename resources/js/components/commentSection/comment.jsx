import React from 'react';

function Comment(props) {
    return (
        <li className="comment">
            <div className="comment-user">
                <img src="/assets/profile-picture.png" alt="User Avatar" className="comment-avatar" />
                <span className="comment-username">{props.userName}</span>
            </div>
            <div className="comment-content">
                <p>{props.commentText}</p>
                <div className="comment-actions">
                    <span className="reply-button">Reply</span>
                </div>
            </div>
            {props.comments && props.comments.length > 0 && (
                <ul className="comment-list">
                    {props.comments.map(comment => (
                        <Comment
                            key={comment.id}
                            commentText={comment.commentText}
                            userName={comment.user.name}
                            comments={comment.comments}
                        />
                    ))}
                </ul>
            )}
        </li>
    );
}

export default Comment;

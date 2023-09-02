import React from 'react';
import { render } from 'react-dom';

function CommentSection() {
    return (
        <div>
            <h5 className="my-2">Reviews</h5>
            <ul className="comment-list">
                <li className="comment">
                    <div className="comment-user">
                        <img src="/assets/profile-picture.png" alt="User Avatar" className="comment-avatar" />
                        <span className="comment-username">User1</span>
                    </div>
                    <div className="comment-content">
                        <p>This is a top-level comment.</p>
                        <div className="comment-actions">
                            <span className="reply-button">Reply</span>
                        </div>
                    </div>

                    <ul className="comment-list">
                        <li className="comment">
                            <div className="comment-user">
                                <img src="/assets/profile-picture.png" alt="User Avatar" className="comment-avatar" />
                                <span className="comment-username">User2</span>
                            </div>
                            <div className="comment-content">
                                <p>This is a nested comment.</p>
                                <div className="comment-actions">
                                    <span className="reply-button">Reply</span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    );
}

render(<CommentSection />, document.getElementById('react-comments'));

export default CommentSection;
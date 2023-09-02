import React, { useState } from 'react';

function CommentEditor(props) {
    const [editText, setEditText] = useState(props.commentText);

    const handleSave = () => {
        props.updateComment(props.commentId, editText);
    };

    const handleChange = (e) => {
        setEditText(e.target.value);
    };

    return (
        <li className='comment'>
            <div className='comment-user'>
                <img src='/assets/profile-picture.png' alt='User Avatar' className='comment-avatar' />
                <span className='comment-username'>{window.USER.name}</span>
            </div>
            <div className='comment-content'>
                <div className='form-group'>
                    <textarea
                        name='commentText'
                        className='form-control'
                        value={editText}
                        onChange={handleChange}
                    ></textarea>
                </div>
                <div className='comment-actions'>
                    <button className='btn btn-primary' onClick={handleSave}>Post</button>
                    <button className='btn reply-button' onClick={() => props.setEditing(props.commentId, false)}>Cancel</button>
                </div>
            </div>
        </li>
    )
}

export default CommentEditor;
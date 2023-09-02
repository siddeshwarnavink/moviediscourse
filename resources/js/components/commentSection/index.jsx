import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';

import Comment from './comment';

function CommentSection() {
    const [comments, setComments] = useState([
        {
            id: 1,
            user: { id: 2, name: 'John doe' },
            commentText: 'This is good',
            comments: [
                {
                    id: 2,
                    user: { id: 3, name: 'Jake' },
                    commentText: 'Yeah!!',
                    comments: []
                }
            ]
        }
    ]);

    return (
        <div>
            <h5 className="my-2">Reviews</h5>
            <ul className="comment-list">

                {comments.map(comment => {
                    return (
                        <Comment 
                            key={comment.id}
                            commentText={comment.commentText}
                            userName={comment.user.name}
                            comments={comment.comments}                            
                        />
                    );
                })}
            </ul>
        </div>
    );
}

const container = document.getElementById('react-comments');
const root = createRoot(container); // createRoot(container!) if you use TypeScript
root.render(<CommentSection />);

export default CommentSection;
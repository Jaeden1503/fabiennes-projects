import React, { useState, useEffect } from 'react'
import TokenManager from '../apis/TokenManager';
import CommentAPI from '../apis/CommentAPI';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrashCan } from '@fortawesome/free-regular-svg-icons'
import '../css/Dashboard.css';

const AllCommentsPage = () => {
  const [comments, setComments] = useState([]);
  const [error, setError] = useState(null);
  const claims = TokenManager.getClaims();

  const getAllComments = () => {
    if(claims?.roles?.includes('ADMIN')) {
      CommentAPI.getAllComments()
      .then(comments => {
        setComments(comments);
        setError(null)
        console.log(comments);
      })
      .catch((err) => setError(err))
    }
    else {
        alert("Oops you don't have permission to do this")
    }
  }

  const deleteComment = (id) => {
    let confirmDelete = window.confirm('Are you sure you want to delete comment?');
    if(confirmDelete){
      if (claims?.roles?.includes('ADMIN') && claims?.userId) {
        CommentAPI.deleteComment(id)
        .then(response => {
          console.log(response);
          alert("comment deleted");
          window.location.reload(false);
        })
        .catch(err => {
          console.log(err)
          alert("couldn't delete comment")
        });
      }
    }
    return;
  }

  useEffect(() => {
    getAllComments();
  }, []);

  if (error) {
    return <div className="App">Unable to load in data... Something went wrong</div>;
  }
  return (
    <div className="content-container">
      <h3>Comments:</h3>
      <p>Here are all the comments displayed, comments can be deleted</p>

      <table className="admin-table">
        <tbody>
          <tr>
            <th>Name</th>
            <th>Comment description</th>
            <th>Date of post</th>
            <th>Belonging to post</th>
            <th>Action</th>
          </tr>
          {comments.map((comment) => (
            <tr key={comment.id}>
              <td style={{"width": "140px"}}>{comment.user.username}</td>
              <td style={{"maxWidth": "100px"}}>{comment.description}</td>
              <td style={{"width": "120px"}}>{comment.date}</td>
              <td style={{"width": "180px"}}>{comment.searchPost.title}</td>
              <td style={{"width": "50px"}}><FontAwesomeIcon icon={faTrashCan} style={{"color": "red", "width": "40px", "cursor": "pointer"}} onClick={(e) => deleteComment(comment.id)} size="1x" /></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AllCommentsPage
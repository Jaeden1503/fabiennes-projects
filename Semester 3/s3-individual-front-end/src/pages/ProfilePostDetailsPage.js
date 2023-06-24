import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import SearchPostAPI from '../apis/SearchPostAPI';
import TokenManager from '../apis/TokenManager';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons'
import '../css/Details.css';

function ProfilePostDetailsPage() {
  // Get the id param from the URL.
  let { id } = useParams();
  //console.log(id);
  const [post, setSearchPost] = useState('');
  const [isLoading, setLoading] = useState(true);
  const claims = TokenManager.getClaims();
  const navigate = useNavigate();

  const getSearchPostDetails = () => {
    if (( claims?.roles?.includes('USER') || claims?.roles?.includes('ADMIN') ) && claims?.userId) {
      SearchPostAPI.getSearchPostAndComments(id)
      .then(data => {
        setSearchPost(data);
        setLoading(false); 
      })
      .catch(err => console.log(err));
    }
  };

  useEffect(() => {  
    getSearchPostDetails();
    console.log(post);
  }, []);

  if (isLoading) {
    return <div className="App">Loading...</div>;
  }
  return (
  <div className='container backbutton-column'>
    <div><FontAwesomeIcon icon={faArrowLeft} style={{cursor: "pointer"}} onClick={() => { navigate(-1)}} size="2x" /></div>
    <div className="profile-post-center">

      <div className="details-post">
        <h6 className="userpost-title">{post.searchPost.title}</h6>
        <div className="yellow-line"></div>

        {post.searchPost.searchingBand === true && (
          <p className="user-subtitle">
            {post.searchPost.instrument} player looking for a band in{" "}
            {post.searchPost.province} | {post.searchPost.date}
          </p>
        )}

        {post.searchPost.searchingBand === false && (
          <p className="user-subtitle">
            Band looking for {post.searchPost.instrument} player in{" "}
            {post.searchPost.province} | {post.searchPost.date}
          </p>
        )}

        <p className="user-comment">{post.searchPost.description}</p>
      </div>
      
      <div className='profile-postdetails-gray-title'>Comments:</div>

      {post.comments.length === 0 ? <div style={{"textAlign": "left", "marginTop": "1rem"}}>There are no comments, wait for people to send a reply</div> : 
      
      post.comments.map((comment) => (
        <div className="profile-postdetails-comment" key={comment.id}>
            <h6 className="profile-postdetails-comments-title">
              {comment.user.username} ({comment.user.email}) on ({comment.date}):
            </h6>

            <p className="profile-user-comment">{comment.description} </p>
          </div>
      ))}
    </div>
  </div>
  );
}

export default ProfilePostDetailsPage;
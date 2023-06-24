import React, { useState, useEffect } from 'react'
import SearchPostAPI from '../apis/SearchPostAPI';
import CommentAPI from '../apis/CommentAPI';
import TokenManager from '../apis/TokenManager';
import ProfileSummary from '../components/ProfileSummary';
import UserDetails from '../components/UserDetails';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faInstagram, faTwitter, faSquareFacebook } from '@fortawesome/free-brands-svg-icons'
import { faPlus } from '@fortawesome/free-solid-svg-icons'
import { faTrashCan } from '@fortawesome/free-regular-svg-icons'
import '../css/Profile.css';

function ProfilePage() {
  const [posts, setSearchPosts] = useState([]);
  const [comments, setComments] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const navigate = useNavigate();
  const claims = TokenManager.getClaims();
  const postCount = posts.length;
  const commentCount = comments.length;

  const getAllSearchPostsByUser = () => {
    if (( claims?.roles?.includes('USER') || claims?.roles?.includes('ADMIN') ) && claims?.userId) {
      SearchPostAPI.getSearchPostsByUser(claims.userId)
      .then(searchPosts => {
        setSearchPosts(searchPosts)
        setError(null)
        console.log(searchPosts);
        setLoading(false);
      })
      .catch(err => setError(err));
    }
  };

  const getAllCommentsByUser = () => {
    if (( claims?.roles?.includes('USER') || claims?.roles?.includes('ADMIN') ) && claims?.userId) {
      CommentAPI.getCommentsByUser(claims.userId)
      .then(comments => {
        setComments(comments)
        setError(null)
        console.log(comments);
        // setLoading(false);
      })
      .catch(err => {
        setError(err)
      });
    }
  };

  const deleteComment = (id) => {
    let confirmDelete = window.confirm('Are you sure you want to delete comment?');
    if(confirmDelete){
      if (( claims?.roles?.includes('USER') || claims?.roles?.includes('ADMIN') ) && claims?.userId) {
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

  const deleteSearchPost = (id) => {
    let confirmDelete = window.confirm('Are you sure you want to delete searchpost?');
    if(confirmDelete){
      if (( claims?.roles?.includes('USER') || claims?.roles?.includes('ADMIN') ) && claims?.userId) {
        SearchPostAPI.deleteSearchPost(id)
        .then(response => {
          console.log(response);
          alert("searchpost deleted");
          window.location.reload(false);
        })
        .catch(err => {
          console.log(err)
          alert("couldn't delete searchpost")
        });
      }
    }
    return;
  }

    useEffect(() => {
      getAllSearchPostsByUser();
      getAllCommentsByUser();
      //console.log(posts)
    }, []);

  if (error) {
    TokenManager.clear();
    navigate("/");
    // window.location.reload(false);
  }
  else if (isLoading) {
    return <div className="App">Loading...</div>;
  }
  return (
    <div className="container" style={{"marginBottom": "6rem"}}>

      {error && <p>an error has occured</p>}

      <div className="grid-view">
        <div className="grid-info">
          {/* user info */}
          <UserDetails/>

          {/* Social media stuff */}
          <div className='social-media-box'>
            <h5>Social media:</h5>
            <div><FontAwesomeIcon icon={faInstagram} style={{"color": "#BF2BAC", "marginBottom": "10px"}} size="2x" /><span style={{"float": "right"}}>Cool_user_546</span></div>
            <div><FontAwesomeIcon icon={faTwitter} style={{"color": "#55ACEE", "marginBottom": "10px"}} size="2x" /><span style={{"float": "right"}}>Cool_user_546</span></div>
            <div><FontAwesomeIcon icon={faSquareFacebook} style={{"color": "#3B5998"}} size="2x" /><span style={{"float": "right"}}>Cool_user_546</span></div>
          </div>
        </div>

        {/* right side, summary info */}
        <div className="grid-info">
          <ProfileSummary postCount={postCount} commentCount={commentCount} />
          
          {/* right side, post info */}
          <div>
            <div className='gray-title'>Posts</div>
            <button className="plus-tag"><FontAwesomeIcon icon={faPlus} size="1x" onClick={() => { navigate(`/createPost`)}}/></button>
            <div className='dividing-line'></div>
            
            {posts.map((searchPost) => (
               <div className='user-post profile-post-column' key={searchPost.id}>
                <div onClick={() => { navigate(`/userPostDetails/${searchPost.id}`)}} >
                  <h6 className='userpost-title'>{searchPost.title}</h6>

                  {searchPost.searchingBand === true && 
                    <p className='user-subtitle'>{searchPost.instrument} player looking for a band in {searchPost.province} | {searchPost.date}</p>}

                  {searchPost.searchingBand === false && 
                    <p className='user-subtitle'>Band looking for {searchPost.instrument} player in {searchPost.province} | {searchPost.date}</p>}
                </div>
                <div className='user-comment-delete'>
                  <FontAwesomeIcon icon={faTrashCan} onClick={(e) => deleteSearchPost(searchPost.id)} size="2x" />
                </div>
              </div>
            ))}
          </div>

          <div>
            <div className='gray-title'>Comments</div>
            <div className='dividing-line'></div>

            {comments.map((comment) =>(
              <div className='user-comment-box profile-post-column' key={comment.id}>
                <div>
                  <h6 className='userpost-title'>{comment.searchPost.title}</h6>

                  {comment.searchPost.searchingBand === true && 
                    <p className='user-subtitle'>{comment.searchPost.instrument} player looking for a band in {comment.searchPost.province} | {comment.searchPost.date}</p>}

                  {comment.searchPost.searchingBand === false && 
                    <p className='user-subtitle'>Band looking for {comment.searchPost.instrument} player in {comment.searchPost.province} | {comment.searchPost.date}</p>}

                    <p className='user-comment'>Me ({comment.date}): {comment.description}</p>
                </div>
                <div className='user-comment-delete'>
                  <FontAwesomeIcon icon={faTrashCan} onClick={(e) => deleteComment(comment.id)} size="2x" />
                </div>
              </div>

            ))}

          </div>   
        </div>
      </div>

    </div>
  );  
}

export default ProfilePage
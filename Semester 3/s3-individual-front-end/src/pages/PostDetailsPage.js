import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom';
import SearchPostAPI from '../apis/SearchPostAPI';
import CommentAPI from '../apis/CommentAPI';
import TokenManager from '../apis/TokenManager';
import { useNavigate } from 'react-router-dom';
import '../css/Background.css';
import '../css/Details.css';
import homeImage from '../images/band_playing_music.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser } from '@fortawesome/free-solid-svg-icons';

function PostDetailsPage() {

  // Get the id param from the URL.
  let { id } = useParams();
  // console.log(id);
  let currentDate = new Date().toJSON().slice(0, 10);
  const claims = TokenManager.getClaims();
  const [post, setSearchPost] = useState('');
  const [isLoading, setLoading] = useState(true);
  const [comment, setComment] = useState('');
  const navigate = useNavigate();

  const getSearchPost = () => {
    SearchPostAPI.getSearchPost((id))
    .then(searchpost => {
      setSearchPost(searchpost)
      setLoading(false);
    })
    .catch(err => console.log(err));
      
    console.log(post);
  };

  const sendReply = () => {
    if (!claims?.roles?.includes('USER') && !claims?.userId) {
        alert("you have to be logged in to comment");
    }
    else if(claims.userId === post.user.id) {
      alert("you cannot comment on your own posts");
    }
    else {
      const commentRequest = { 
        description: comment,
        date: currentDate,
        userId: claims.userId,
        searchPostId: post.id      
      }

      CommentAPI.createComment(commentRequest)
      .then(response => {
        console.log(response)
        alert("comment created");
        navigate(`/search`);
        // window.location.reload(false);
      })
      .catch(err => {
        console.log(err)
        alert("couldn't create comment")
      });
    }
    console.log(comment);      
  }

  useEffect(() => { 
      document.body.classList.add("overflow-hidden");
      getSearchPost();

      return () => {
          document.body.classList.remove("overflow-hidden");
      };   
  }, []);


  if (isLoading) {
    return <div className="App">Loading...</div>;
  }
  return (
    <div>
        <div className='middle_top'></div>
        <div className='left_top'></div>
        <div className='left_bottom'></div>
        <div className='middle_center'></div>
        <div className='right_bottom'><img className='img_band' src={homeImage} alt="band playing music" /></div>

        <div className="container" style={{"marginBottom": "6rem"}}>
          <div className='grid-details'>

            <div className="details-user-info-box">
              <FontAwesomeIcon icon={faCircleUser} style={{ "--fa-primary-color": "#2d2344", "--fa-secondary-color": "#d9d9d9", paddingLeft: "5px" }} size="10x"/>
              <h6 className='details-username-title'>{post?.user.username}</h6>
              <p>{post?.user.email}</p>
              <div>
                <div className="tag">Guitar</div>
                <div className="tag">Rock</div>
              </div>

              <h5 className="user-title">About the poster:</h5>
              <div className="aboutme-text">{post?.user.about !== null ? post?.user.about : "no information provided"}</div>
            </div>

            <div className="details-post-row">
              <div className='details-post'>
                  <h6 className='userpost-title'>{post.title}</h6>
                  <div className='yellow-line'></div>

                  {post.searchingBand === true && 
                    <p className='user-subtitle'>{post.instrument} player looking for a band in {post.province} | {post.date}</p>}

                  {post.searchingBand === false && 
                    <p className='user-subtitle'>Band looking for {post.instrument} player in {post.province} | {post.date}</p>}

                  <p className='user-comment'>{post.description}</p>
              </div>


              <div className='details-reply'>
                  <h6 className='userpost-title'>Interested? Send a reply!</h6>
                  
                  <div className='reply-form'>
                    <textarea id="freeform" name="freeform" rows="4" cols="54" maxLength="230" 
                      placeholder='maximum of 230 characters' onChange={(e) => { setComment(e.target.value); }}>
                    </textarea>
                    {/* <input type="submit" value="Submit"></input> */}
                    <button onClick={sendReply} disabled={comment === '' ? true : false}>Send</button>
                  </div>
              </div>
            </div>
          </div>
        </div>
    </div>
  )
}

export default PostDetailsPage
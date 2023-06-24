import React, { useState, useEffect } from 'react'
import '../css/Profile.css';
import { useNavigate } from 'react-router-dom';
import TokenManager from '../apis/TokenManager';
import UserAPI from '../apis/UserAPI';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCircleUser, faPenToSquare } from '@fortawesome/free-solid-svg-icons'

function UserDetails() {
  const navigate = useNavigate();
  const claims = TokenManager.getClaims();
  const [user, setUser] = useState('');

  const getUserInfo = () => {
    if (( claims?.roles?.includes('USER') || claims?.roles?.includes('ADMIN') ) && claims?.userId) {
      UserAPI.getUser(claims.userId)
      .then(user => {
        setUser(user); 
        console.log(user);
      })
      .catch(err => console.log(err));
    }
  }

  useEffect(() => {
    getUserInfo();
  }, []);

  return (
    <div className="user-info-box">
      <FontAwesomeIcon onClick={() => { navigate(`/editProfile`); }} className="edit-user" icon={faPenToSquare} size="1x"/>
      <FontAwesomeIcon icon={faCircleUser} style={{ "--fa-primary-color": "#2d2344", "--fa-secondary-color": "#d9d9d9", paddingLeft: "35px" }} size="10x"/>
      <h6 className='username-title'>{user?.username}</h6>
      <p>{user?.email}</p>
      <div>
        <div className="tag">Guitar</div>
        <div className="tag">Rock</div>
      </div>

      <h5 className="user-title">About me:</h5>
      <div className="aboutme-text">{user?.about !== null ? user.about : "write something about yourself!"}</div>
    </div>
  );
}

export default UserDetails
import React, { useState, useEffect } from 'react'
import '../css/Profile.css';
import { useNavigate } from 'react-router-dom';
import TokenManager from '../apis/TokenManager';
import UserAPI from '../apis/UserAPI';
import homeImage from '../images/band_playing_music.png'

function EditProfilePage() {
  const navigate = useNavigate();
  const claims = TokenManager.getClaims();
  const [user, setUser] = useState('');

  const getUserInfo = () => {
    if ((claims?.roles?.includes("USER") || claims?.roles?.includes("ADMIN")) && claims?.userId) {
      UserAPI.getUser(claims.userId)
        .then((user) => {
          setUser(user);
          console.log(user);
        })
        .catch((err) => console.log(err));
    }
  };

  const updateProfile = (event) => {
    event.preventDefault();
    console.log(user);

    if (claims?.roles?.includes("USER")  && claims?.userId) {
        UserAPI.updateUser(claims.userId, user)
          .then((response) => {
            console.log(response);
            alert("successfully updated profile");
            navigate(`/profile`);
          })
          .catch((err) => {
            console.log(err.response.status);
            if (err.response.status === 400) {
                alert("username already in use");
            }
            else {
                alert("could not update profile");
            }
        });
      }
  }

  useEffect(() => {
    getUserInfo();

    document.body.classList.add("overflow-hidden");
    return () => {
        document.body.classList.remove("overflow-hidden");
    };
  }, []);

  return (
    <div className="container edit-profile">
      <div className="middle_top"></div>
      <div className="left_top"></div>
      <div className="left_bottom"></div>
      <div className="middle_center"></div>
      <div className="right_bottom"></div>
      <div className='right_bottom'><img className='img_band' src={homeImage} alt="band playing music" /></div>



      <div className="create-center" >
        <h2>Edit your profile!</h2>
          <form onSubmit={updateProfile}>

            <div className="create-grid-container">
              <div>Username<span style={{"color": "red"}}>*</span>:</div>
              <input type="text" required value={user.username} onChange={(e) => { setUser({...user, username:e.target.value}); }} ></input>

              <div>email<span style={{"color": "red"}}>*</span>:</div>
              <input type="email" required value={user.email} onChange={(e) => { setUser({...user, email:e.target.value}); }} ></input>

              <div>about:</div>
              <textarea maxLength="230" value={user?.about === null? "" : user.about} onChange={(e) => { setUser({...user, about:e.target.value}); }} ></textarea>

              <div>password<span style={{"color": "red"}}>*</span>:</div>
              <input type="password" required onChange={(e) => { setUser({...user, password:e.target.value}); }} ></input>

              <br/>
            </div>
            <button className="search-button" type='submit'>Save</button>
            <button className="search-button" onClick={() => { navigate(-1)} }>Cancel</button>
          </form>
      </div>
    </div>

  );
}

export default EditProfilePage;

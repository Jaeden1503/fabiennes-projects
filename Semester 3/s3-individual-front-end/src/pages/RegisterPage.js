import React, { useState, useEffect } from 'react'
import UserAPI from '../apis/UserAPI';
import '../css/Background.css'
import homeImage from '../images/band_playing_music.png'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAt, faKey, faUser } from '@fortawesome/free-solid-svg-icons'
import { Card } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function RegisterPage() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");

  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();

    const registerRequest = {
      username: username,
      email: email,
      password: password,
    };

    UserAPI.createUser(registerRequest)
      .then((response) => {
        alert("succesfully created account");
        navigate("/login");
          //window.location.reload(false);
      })
      .catch((error) => { 
        console.error(error)
        if (error.response.status === 400)  {
          alert("Username already exists")
        }
    })
  };

    //makes sure the boxes on the background don't cause vertical scrolling
  useEffect(() => {
    document.body.classList.add("overflow-hidden");
    return () => {
      document.body.classList.remove("overflow-hidden");
    };
  }, []);

  return (
    <div className="container">
      <div className="middle_top"></div>
      <div className="left_top"></div>
      <div className="left_bottom"></div>
      <div className="middle_center"></div>
      <div className="right_bottom"></div>
      <div className="right_bottom"><img className="img_band" src={homeImage} alt="band playing music" /></div>


      <Card className="loginCard">
        <Card.Header><h3>Sign up</h3></Card.Header>

        <Card.Body>     
          <div className="input-group form-group">           
            <span className="input-group-text"><FontAwesomeIcon icon={faUser} /></span>        
            <input type="text" id='username' className="form-control" onChange={(e) => setUsername(e.target.value)} placeholder="username"></input>
          </div>
          <br/>

          <div className="input-group form-group">         
            <span className="input-group-text"><FontAwesomeIcon icon={faAt} /></span>        
            <input type="email" id='email' className="form-control" onChange={(e) => setEmail(e.target.value)} placeholder="email"></input>
          </div>
          <br/>

          <div className="input-group form-group">
            <span className="input-group-text"><FontAwesomeIcon icon={faKey} /></span>
            <input type="password" id='password' className="form-control" onChange={(e) => setPassword(e.target.value)} placeholder="password"></input>
          </div>

          <button className="login-button" data-cy="submit" onClick={handleSubmit}>Sign up</button>    
        </Card.Body>

        <Card.Footer>
          <div className="login-footer">Already have an account? <a href="/login">Log in</a></div>
        </Card.Footer>
      </Card>
    </div>
  );
}

export default RegisterPage
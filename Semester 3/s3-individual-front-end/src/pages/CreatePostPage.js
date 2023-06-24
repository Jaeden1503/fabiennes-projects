import React, { useState, useEffect } from 'react';
import { Form } from 'react-bootstrap';
import TokenManager from '../apis/TokenManager';
import SearchPostAPI from '../apis/SearchPostAPI';
import { useNavigate } from 'react-router-dom';
import '../css/CreatePost.css';
import homeImage from '../images/band_playing_music.png'

function CreatePostPage() {
  let currentDate = new Date().toJSON().slice(0, 10);
  const claims = TokenManager.getClaims();
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [band, setBandParam] = useState(true);
  const [instrument, setInstrumentParam] = useState("");
  const [province, setProvinceParam] = useState("");
  const navigate = useNavigate();

  const createPost = () => {
    if (!claims?.roles?.includes('USER') && !claims?.userId) {
      alert("you have to be logged in to create a post");
    }
    else {
      const post = {
        title: title,
        description: description,
        searchingBand: band,
        date: currentDate,
        instrument: instrument,
        province: province,
        userId: claims.userId
      }

      console.log(post)
      
      SearchPostAPI.createSearchPost(post)
      .then(response => {
        console.log(response);
        alert("post created");
        navigate(`/profile`);
      })
      .catch(err => {
        console.log(err.response.status);
        if (err.response.status === 400) {
            alert("All fields must be filled");
        }
        else {
            alert("Could not create post");
        }
    });
    }
  }

    //makes sure the boxes on the background don't cause vertical scrolling
    useEffect(() => {
      document.body.classList.add("overflow-hidden");
        return () => {
            document.body.classList.remove("overflow-hidden");
        };
    }, []);

  return (
    <div className="container" style={{ marginBottom: "6rem" }}>
      <div className="middle_top"></div>
      <div className="left_top"></div>
      <div className="left_bottom"></div>
      <div className="middle_center"></div>
      <div className="right_bottom"></div>
      <div className='right_bottom'><img className='img_band' src={homeImage} alt="band playing music" /></div>


      <div className="create-center">
        <h2>Create a post</h2>
        <div className="create-grid-container">

          <div>Title:</div>
          <input type="text" placeholder='maximum of 70 characters' onChange={(e) => { setTitle(e.target.value); }}></input>

          <div>Description:</div>
          <div>
            <textarea id="freeform" name="freeform" rows="4" cols="54" maxLength="230" placeholder="maximum of 230 characters" onChange={(e) => { setDescription(e.target.value); }} ></textarea>
          </div>

          <div>Choose category:</div>
          <div>
            <Form.Check inline name="group1" label="I'm a musician looking for a band" type="radio" value={true} onChange={(e) => { setBandParam(e.target.value); }} defaultChecked/>
            <Form.Check inline name="group1" label="We're a band looking for a musician" type="radio" value={false} onChange={(e) => { setBandParam(e.target.value); }}/>
          </div>

          <div>{band === true ? "I am a" : "We are looking for a:"}</div>
          <div>
            <select
              onChange={(e) => {
                setInstrumentParam(e.target.value);
              }}
              className="custom-select"
              aria-label="Filter by intruments"
            >
              <option value="">...</option>
              <option value="guitar">Guitarist</option>
              <option value="piano">Pianist</option>
              <option value="drums">Drummer</option>
              <option value="bass">Bassist</option>
              <option value="vocalist">Singer</option>
              <option value="trumpet">Trumpeter</option>
              <option value="violin">Violinist</option>
              <option value="flute">Flutist</option>
            </select>
            <span className="focus"></span>
          </div>

          <div>Province:</div>
          <div>
            <select
              onChange={(e) => {
                setProvinceParam(e.target.value);
              }}
              className="custom-select"
              aria-label="Filter by province"
            >
              <option value="">...</option>
              <option value="Noord-brabant">Noord-Brabant</option>
              <option value="Limburg">Limburg</option>
              <option value="Noord-Holland">Noord-Holland</option>
              <option value="Zuid-Holland">Zuid-Holland</option>
              <option value="Gelderland">Gelderland</option>
              <option value="Utrecht">Utrecht</option>
              <option value="Zeeland">Zeeland</option>
              <option value="Overijssel">Overijssel</option>
              <option value="Flevoland">Flevoland</option>
              <option value="Drenthe">Drenthe</option>
              <option value="Groningen">Groningen</option>
              <option value="Friesland">Friesland</option>
            </select>
            <span className="focus"></span>
          </div>
        </div>
        <button className="search-button" onClick={createPost}>Post</button>
        <button className="search-button" onClick={() => { navigate(-1)} }>Cancel</button>
      </div>
    </div>
  );
}

export default CreatePostPage
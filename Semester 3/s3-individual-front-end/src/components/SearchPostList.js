import React from 'react'
import { Card } from 'react-bootstrap';
import '../css/SearchpostCard.css';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCircleUser } from '@fortawesome/free-solid-svg-icons'

function SearchPostList(props) {
  const navigate = useNavigate();

  //console.log(props.searchPosts.slice(0).reverse());
  return (
    //returns a card list of searchposts, newest on top
    <div id='list-of-posts'>
      {props.searchPosts.slice(0).reverse().map((searchPost) => (

        <Card className="cardDesign" onClick={() => { navigate(`/postDetails/${searchPost.id}`)}} key={searchPost.id}>
          <Card.Header className="cardTitle">{searchPost.title}</Card.Header>
          <Card.Body className="cardBody">
          {searchPost.searchingBand === true &&
            <Card.Text className="cardSubtitle">{searchPost.instrument} player looking for a band in {searchPost.province}</Card.Text>}

          {searchPost.searchingBand === false &&
            <Card.Text className="cardSubtitle">Band looking for {searchPost.instrument} player in {searchPost.province}</Card.Text>}

            <Card.Text className="cardDesc">{searchPost.description}</Card.Text>
          </Card.Body>
          <Card.Footer className='cardFooter'>
            <FontAwesomeIcon icon={faCircleUser} style={{"--fa-primary-color": "#2d2344", "--fa-secondary-color": "#d9d9d9" }} size="3x" />
            <div className="cardFooterText">
              <h6>{searchPost.user.username}</h6>
              <Card.Subtitle>{searchPost.date}</Card.Subtitle>
            </div>
          </Card.Footer>
        </Card>
      ))}
   
    </div>
  );
}

export default SearchPostList;
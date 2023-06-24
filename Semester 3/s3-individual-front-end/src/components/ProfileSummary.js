import React from 'react'
import '../css/Profile.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCommentDots, faNewspaper } from '@fortawesome/free-regular-svg-icons'

function ProfileSummary(prop) {
  return (
    <div className="summary-column">
    <div className='summary-box-posts'>
      <div style={{"float": "left"}}><FontAwesomeIcon icon={faNewspaper} className='newpaper-icon' size="6x" /></div>

      <div style={{"float": "right"}}>
        <span className='summary-title'>{prop.postCount}</span>  
        <span className='summary-post-text'>posts</span>
      </div>
      
    </div>
    <div className='summary-box-comments'>
      <div style={{"float": "left"}}><FontAwesomeIcon icon={faCommentDots} className='comment-icon' size="6x" /></div>

      <div style={{"float": "right"}}>
        <span className='summary-title'>{prop.commentCount}</span>  
        <span className='summary-comment-text'>comments</span>
      </div>
    </div>
  </div>
  )
}

export default ProfileSummary
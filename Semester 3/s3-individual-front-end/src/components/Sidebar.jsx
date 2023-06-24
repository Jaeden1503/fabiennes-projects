import React from "react";
import '../css/Dashboard.css'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChartLine, faUserPen } from "@fortawesome/free-solid-svg-icons";
import { faNewspaper, faComments } from '@fortawesome/free-regular-svg-icons'
import Nav from 'react-bootstrap/Nav';

function Sidebar() {
  return (
    <div className="sidebar">
      <div className="sidebar-row">
        <div className='logo'><b>FyG</b></div>
        <div className='sidebar-links'>
          <Nav.Link href="/dashboard"><FontAwesomeIcon icon={faChartLine} size="1x" />Statistics</Nav.Link>
          <Nav.Link href="/posts"><FontAwesomeIcon icon={faNewspaper} size="1x" />Posts</Nav.Link>
          <Nav.Link href="/users"><FontAwesomeIcon icon={faUserPen} size="1x" />Users</Nav.Link>
          <Nav.Link href="/comments"><FontAwesomeIcon icon={faComments} size="1x" />Comments</Nav.Link>
        </div>
      </div>
    </div>
  );
}

export default Sidebar;

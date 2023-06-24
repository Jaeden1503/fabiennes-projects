import React, { useState } from 'react';
import '../css/Filter.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faChevronUp, faChevronDown } from '@fortawesome/free-solid-svg-icons'
import { Form } from 'react-bootstrap';

function Filter(props) {
  const [open, setOpen] = useState(false);

  const [bandParam, setBandParam] = useState(false);
  const [instrumentParam, setInstrumentParam] = useState("");
  const [provinceParam, setProvinceParam] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    props.onFilter(bandParam, instrumentParam, provinceParam);
  }

  const handleClear = (event) => {
    event.preventDefault();
    props.onNoFilter();
  }

  return (
    <div>
    {/* className={`expandable-box ${open === true ? 'active' : ''}`}  */}
      <div className="search-bar" onClick={() => setOpen(!open)}>
        <div className='search-text'>Open to search</div>

        <div className='dropdown-icon'>
          {open === false && (
            <FontAwesomeIcon icon={faChevronUp} style={{ color: "#2d2344" }} size="2x" /> )}
          {open === true && (
            <FontAwesomeIcon icon={faChevronDown} style={{ color: "#2d2344" }} size="2x" /> )}
          </div>
      </div>

      {open === true && (
        <div className="filter-field">
          <div className="grid-container">
            <div>Choose category:</div>
            <div>
              <Form.Check inline name="group1" label="I'm a musician looking for a band" type="radio" value={false}
              onChange={(e) => { setBandParam(e.target.value); }} defaultChecked />
              <Form.Check inline name="group1" label="We're a band looking for a musician" type="radio" value={true}
              onChange={(e) => { setBandParam(e.target.value); }} />
            </div>
            <div>{bandParam ? "We are looking for a:" : "I am a:"}</div>
            <div>
              <select onChange={(e) => { setInstrumentParam(e.target.value); }}
                className="custom-select" aria-label="Filter by intruments" >
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
              <span className="focus"></span></div>

            <div>Province:</div>
            <div>            
              <select onChange={(e) => { setProvinceParam(e.target.value); }}
              className="custom-select" aria-label="Filter by province">
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
            <span className="focus"></span></div>
          </div>
          <button className="search-button" onClick={handleSubmit}>Search</button>
          <button className="search-button" onClick={handleClear}>Clear</button>

        </div>
      )}
    </div>
  );
}

export default Filter
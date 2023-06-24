import React, { useEffect } from 'react'
import '../css/Homepage.css'
import '../css/Background.css'
import homeImage from '../images/band_playing_music.png'

function HomePage() {

  //makes sure the boxes on the background don't cause vertical scrolling
  useEffect(() => {
    document.body.classList.add("overflow-hidden");
    return () => {
        document.body.classList.remove("overflow-hidden");
    };
  }, []);


  return (
    <div className='container'>
      <div className='middle_top'></div>
      <div className='left_top'></div>
      <div className='left_bottom'></div>
      <div className='middle_center'></div>
      <div className='right_bottom'><img className='img_band' src={homeImage} alt="band playing music" /></div>

      {/* all the text of the home page */}
      <h1 className='title'>Know your vibe</h1>
      <p className='subtitle'>Start looking for your garageband today</p>
      <p className='text_box'>Find your garageband is the easiest way to find new musicians for your band. <a href="/search">Click here to start!</a></p>
    </div>
  )
}

export default HomePage
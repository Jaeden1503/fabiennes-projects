import React, { useState, useEffect } from 'react'
import StatisticsAPI from '../apis/StatisticsAPI';
import TokenManager from '../apis/TokenManager';
import '../css/Dashboard.css';
import Chart from "react-apexcharts";
import BvsMChart from '../components/BvsMChart';
import InstrumentChart from '../components/InstrumentChart';

function Statistics() {
  const [popularPosts, setPopularPosts] = useState([undefined]);
  const [bandVSMusician, setBandVSMusician] = useState('');
  const [instrument, setInstrument] = useState('');
  const [error, setError] = useState(null);
  //const [isLoading, setLoading] = useState(true);
  const claims = TokenManager.getClaims();

  const getStatistics = () => {
    if (claims?.roles?.includes("ADMIN")) {
      StatisticsAPI.getTopPosts()
        .then((popPosts) => {
          console.log(popPosts);
          setPopularPosts(popPosts);
          //setLoading(false);
          setError(null);
        })
        .catch((err) => setError(err));
    }
  };

  const getVSCount = () => {
    if (claims?.roles?.includes("ADMIN")) {
      StatisticsAPI.getVSCount()
        .then((data) => {
          console.log(data);
          setBandVSMusician(data);
          setError(null);
        })
        .catch((err) => setError(err));
    }
  };

  const getInstrumentCount = () => {
    if (claims?.roles?.includes("ADMIN")) {
      StatisticsAPI.getInstrumentCount()
        .then((instrument) => {
          console.log(instrument);
          setInstrument(instrument);
          setError(null);
        })
        .catch((err) => setError(err));
    }
  };

  const series = [
    {
      name: "Amount", //hover info of bars, should be post title
      data: [
        popularPosts[0] === undefined ? 0 : popularPosts[0].amountComments,
        popularPosts[1] === undefined ? 0 : popularPosts[1].amountComments,
        popularPosts[2] === undefined ? 0 : popularPosts[2].amountComments,
        popularPosts[3] === undefined ? 0 : popularPosts[3].amountComments,
        popularPosts[4] === undefined ? 0 : popularPosts[4].amountComments,
        popularPosts[5] === undefined ? 0 : popularPosts[5].amountComments,
        popularPosts[6] === undefined ? 0 : popularPosts[6].amountComments,
        popularPosts[7] === undefined ? 0 : popularPosts[7].amountComments,
        popularPosts[8] === undefined ? 0 : popularPosts[8].amountComments,
        popularPosts[9] === undefined ? 0 : popularPosts[9].amountComments,
      ],
    },
  ];

  const options = {
    chart: {
      id: "bar-chart",
      toolbar: {
        show: false,
      },
    },
    xaxis: {
      categories: [
        popularPosts[0] === undefined ? "no data" : popularPosts[0].searchPostTitle,
        popularPosts[1] === undefined ? "no data" : popularPosts[1].searchPostTitle,
        popularPosts[2] === undefined ? "no data" : popularPosts[2].searchPostTitle,
        popularPosts[3] === undefined ? "no data" : popularPosts[3].searchPostTitle,
        popularPosts[4] === undefined ? "no data" : popularPosts[4].searchPostTitle,
        popularPosts[5] === undefined ? "no data" : popularPosts[5].searchPostTitle,
        popularPosts[6] === undefined ? "no data" : popularPosts[6].searchPostTitle,
        popularPosts[7] === undefined ? "no data" : popularPosts[7].searchPostTitle,
        popularPosts[8] === undefined ? "no data" : popularPosts[8].searchPostTitle,
        popularPosts[9] === undefined ? "no data" : popularPosts[9].searchPostTitle,
      ],
      labels: {
        show: true,
        rotate: -45,
        rotateAlways: true,
        style: {
          fontSize: "11px",
          fontFamily: "Montserrat, Helvetica, Arial, sans-serif",
        },
      },
      title: { text: "Post titles",
        style: { fontSize: "12px", fontFamily: "Montserrat, Helvetica, Arial, sans-serif" } 
      },
    },
    yaxis: {
      title: { text: "Amount of Comments", 
        style: { fontSize: "13px", fontFamily: "Montserrat, Helvetica, Arial, sans-serif" } 
      },
    },
    dataLabels: { //doesn't show the amount on the bar itself
      enabled: false
    },
    colors: "#FF4560"
  };
      
  useEffect(() => {
    getStatistics();
    getVSCount();
    getInstrumentCount();
  }, []);

  if (error) {
    // TokenManager.clear();
    // navigate("/");
    return <div className="App">Something went wrong...</div>;
  }
  else if (popularPosts.length===undefined) {
    return <div className="App">Loading...</div>;
  }
  return (
    <div className='content-container'>  
      <div>
        <h3>Statistics:</h3>
        <p>Here are a few charts displayed, that show different types of calculated data</p>

        <h6><b>Top 10 Most Commented Post</b></h6>
        <Chart options={options} type="bar" series={series} width="50%" />
        <br/>
        <div className="chart-divider"></div>
        <br/>
        <BvsMChart data={bandVSMusician}/>
        <br/>
        <div className="chart-divider"></div>
        <br/>
        <InstrumentChart data={instrument}/>
      </div>
    </div>
  );
}

export default Statistics
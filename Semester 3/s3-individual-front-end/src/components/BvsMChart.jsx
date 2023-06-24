import React from "react";
import "../css/Dashboard.css";
import Chart from "react-apexcharts";

function BvsMChart(props) {
  const series = [props.data.searchingForBand, props.data.searchingForMusician];

  const options = {
    labels: ['Looking for a band', 'Looking for a musician'],
    responsive: [{
      breakpoint: 480,
      options: {
        chart: {
          width: 200
        }
      }
    }],
    // title: { 
    //   text: "Amount of posts looking for a Band vs a Musician", 
    //   align: 'center', 
    //   offsetX: 10,
    //   style: { fontSize: "12px", fontFamily: "Montserrat, Helvetica, Arial, sans-serif" } 
    // },
    fill: {
      colors: ['#FEB019', '#00E396']
    },
    legend: {
      markers: { fillColors: ['#FEB019', '#00E396']},
      fontSize: "13px", 
      fontFamily: "Montserrat, Helvetica, Arial, sans-serif"
    }
  };

  return (  
    <div>
      <h6><b>Amount of posts looking for a Band vs a Musician</b></h6>
      <Chart options={options} type="pie" series={series} width="40%"/>
    </div>
  );
}

export default BvsMChart;
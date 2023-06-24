import React from "react";
import "../css/Dashboard.css";
import Chart from "react-apexcharts";

function InstrumentChart(props) {
  const series = [{
    name: "Amount",
    data: [props.data.bass, props.data.drums, props.data.flute, props.data.guitar, props.data.piano, props.data.singer, props.data.trumpet]
  }]

  const options = {
    chart: {
      type: 'bar',
      height: 550,
      toolbar: {
        show: false
      }
    },
    plotOptions: {
      bar: {
        borderRadius: 4,
        horizontal: true,
      }
    },
    dataLabels: {
      enabled: false
    },
    colors: "#775DD0",
    xaxis: {
      categories: ['Bass', 'Drums', 'Flute', 'Guitar', 'Piano', 'Singer', 'Trumpet'],
      title: { text: "Amount",
        style: { fontSize: "12px", fontFamily: "Montserrat, Helvetica, Arial, sans-serif" } 
        },
      
    },
    yaxis: {
        title: { text: "Instruments", 
            style: { fontSize: "13px", fontFamily: "Montserrat, Helvetica, Arial, sans-serif" } 
        },
        labels: {
            show: true,
            style: {
              fontSize: "11px",
              fontFamily: "Montserrat, Helvetica, Arial, sans-serif",
            },
          },
      }
  }

  return (
    <div>
      <h6><b>Amount of posts per Instrument</b></h6>
      <Chart options={options} type="bar" height={400} series={series}  width="50%"/>
    </div>
  );
}

export default InstrumentChart;

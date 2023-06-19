import React, { useEffect, useState } from "react";
import Chart from "react-apexcharts";

const IntensityAndPerformanceGraph = ({ data }) => {
    const [dataArray, setDataArray] = useState([]);

    useEffect(() => {
        prepareData();
    }, [data]);

    const prepareData = () => {
        const newArray = [];
        data.forEach((session) => {
            const obj = { x: session.performanceRating, y: session.intensityRating };
            newArray.push(obj);
        });
        setDataArray(newArray);
    };

    const graphParams = {
        series: [{
            data: dataArray,
        }],
        options: {
            chart: {
                height: 350,
                type: 'scatter',
            },
            xaxis: {
                tickAmount: 1,
            },
            yaxis: {
                tickAmount: 10,
            },
        },
    };

    console.log(dataArray);

    return (
        <div>
            <Chart
                options={graphParams.options}
                series={graphParams.series}
                type="scatter"
                width={400}
                height={350}
            />
        </div>
    );
};

IntensityAndPerformanceGraph.displayName = "IntensityAndPerformanceGraph";

export default IntensityAndPerformanceGraph;


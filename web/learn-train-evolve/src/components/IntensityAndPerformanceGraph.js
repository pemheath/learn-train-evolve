import React, { useEffect, useState } from "react";

import {VictoryChart, VictoryScatter} from "victory";

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


    console.log(dataArray);

    return (
        <div>
            <VictoryChart>
                <VictoryScatter
                    data={dataArray}/>
            </VictoryChart>
        </div>
    );
};

IntensityAndPerformanceGraph.displayName = "IntensityAndPerformanceGraph";

export default IntensityAndPerformanceGraph;


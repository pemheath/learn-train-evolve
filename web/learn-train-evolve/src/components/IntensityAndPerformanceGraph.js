import React, {useEffect} from "react";
import {VictoryBoxPlot, VictoryChart} from "victory";



const IntensityAndPerformanceGraph = ({data}) => {

    const [dataArray, setDataArray] = React.useState([]);

    // prepare the data for the chart
    useEffect(() => {
        if (!data ) {
            return <div>Loading...</div>;
        }

        const map = new Map();
        data.forEach(session => {
            const performanceRating = session.performanceRating;
            if (map.has(performanceRating)) {
                const list = map.get(performanceRating);
                console.log("list for performance rating" + performanceRating + "is" + list);
                list.push(session.intensityRating);
                map.set(performanceRating, list);
            } else {
            map.set(performanceRating, [session.intensityRating]);}
        });
        const dataArray = Array.from(map, ([x, y]) => ({x, y}));
        setDataArray(dataArray);
        console.log(dataArray);
    }, []);

    if(dataArray.length===0) {
        return <div>Loading...</div>;
    }
        return (
            <div style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <div style={{ width: '80%', maxWidth: '400px', position: 'relative',}}>
                    <VictoryBoxPlot
                        boxWidth={20}
                        data={dataArray}
                />

                </div>
            </div>
        );

}

IntensityAndPerformanceGraph.displayName="IntensityAndPerformanceGraph";

export default IntensityAndPerformanceGraph;
import React, {useEffect} from "react";
import {VictoryPie} from "victory";



const PieChartByType = ({data}) => {

    const [dataArray, setDataArray] = React.useState([]);

    // prepare the data for the chart
    useEffect(() => {
        if (!data) {
            return <div>Loading...</div>;
        }
        const typeFrequencyMap = new Map();
        data.forEach(session => {
            const type = session.type;
            if (typeFrequencyMap.has(type)) {
                typeFrequencyMap.set(type, typeFrequencyMap.get(type) + 1);
            } else {
            typeFrequencyMap.set(type, 1);}
        });
        console.log("map is: " + typeFrequencyMap.toString());
        const dataArray = Array.from(typeFrequencyMap, ([x, y]) => ({x, y}));
        setDataArray(dataArray);
        console.log("data array is: " + dataArray.toString());
    }, []);

    if(dataArray.length===0) {
        return <div>Loading...</div>;
    }




        return (
            <div style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <div style={{ width: '80%', maxWidth: '400px', position: 'relative',}}>
                <VictoryPie
                    data={dataArray}
                    width={400}
                    height={400}
                />
                </div>
            </div>
        );

}

PieChartByType.displayName="PieChartByType";

export default PieChartByType;
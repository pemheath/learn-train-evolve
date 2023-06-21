import React, {useEffect} from "react";
import {VictoryPie} from "victory";
import background1 from '../project images/background1.jpeg';




const PieChartByType = ({data}) => {
    console.log(data);

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
    }, [data]);

    if(dataArray.length<=3) {
        return (
            <div style={{ backgroundSize: "contain", backgroundRepeat: "no-repeat", backgroundImage: `url(${background1})`, width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center',
            color:"white"}}>
            </div>);
    }

        return (
            <div style={{backgroundColor: "var(--amplify-colors-teal-90)", width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'baseline' }}>
                <div style={{ width: '80%', maxWidth: '400px', position: 'relative',}}>
                    <h2 style={{ color: 'hsl(190, 100%, 20%)' }}>Training By Class Type</h2>
                <VictoryPie
                    data={dataArray}
                    width={400}
                    height={400}
                    labelRadius={({ innerRadius }) => innerRadius + 5 }
                    innerRadius={50}
                    style={{ labels: { fill: "white", fontSize: 15, fontWeight: "bold" } }}
                    colorScale={["var(--amplify-colors-teal-20)", "var(--amplify-colors-teal-40)", "var(--amplify-colors-teal-60)", "var(--amplify-colors-teal-80)"]}
                />
                </div>
            </div>
        );

}

PieChartByType.displayName="PieChartByType";

export default PieChartByType;
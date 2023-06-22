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

    if(data.length<=3) {
        return (
            <div style={{ backgroundSize: "contain", backgroundRepeat: "no-repeat", backgroundImage: `url(${background1})`, width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center',
            color:"white"}}>
            </div>);
    }

        return (
            <div style={{width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <div style={{ width: '80%', height: '80%', position: 'inherit', borderRadius: '10px', backgroundColor: "var(--amplify-colors-teal-90)"}}>
                    <h2 style={{ color: 'white', textAlign: 'center'}}>Training By Class Type</h2>
                    <div style= {{ position: 'relative', top: '58%', left: '50%', transform: 'translate(-50%, -65%)'}}>
                        <VictoryPie
                            data={dataArray}
                            width={500}
                            height={500}
                            labelRadius={({ innerRadius }) => innerRadius + 5 }
                            labelPlacement={"parallel"}
                            innerRadius={50}
                            style={{ labels: { fill: "white", fontSize: 15, fontWeight: "bold" } }}
                            colorScale={["var(--amplify-colors-teal-40)", "var(--amplify-colors-teal-20)", "var(--amplify-colors-teal-60)", "var(--amplify-colors-teal-80)"]}
                        />
                    </div>
                </div>
            </div>
        );

}

PieChartByType.displayName="PieChartByType";

export default PieChartByType;
import React, {useEffect} from "react";
import {useState} from 'react';
import {Chart as ChartJS, BarElement, CategoryScale, LinearScale, Title} from "chart.js";
import {Bar} from "react-chartjs-2";
import {useTheme} from "@aws-amplify/ui-react";

const TrainingFrequencyByWeek = ({data}) => {
const [keysArray, setKeysArray] = useState([]);
const [valuesArray, setValuesArray] = useState([]);

const{tokens} = useTheme();

    useEffect(() =>{
        prepareData(data);
    }, [data]);

    const prepareData = (data) => {
        const array= data.map((userTrainingSession) => {
            const date = new Date(userTrainingSession.timeAndDate*1000);
            date.setDate(date.getDate() - date.getDay() + 1); // Set to Monday of the week
            const weekOf = `Week of ${date.toDateString()}`;
            return weekOf;
        });

        const frequencyMap = array.reduce((map, value) => {
            if (map.has(value)) {
                map.set(value, map.get(value) + 1);
            } else {
                map.set(value, 1);
            }
            return map;
        }, new Map());

        const newArray = Array.from(frequencyMap.keys());
        const otherArray = Array.from(frequencyMap.values());
        setKeysArray(newArray);
        setValuesArray(otherArray);

    };

    ChartJS.register(BarElement, CategoryScale, LinearScale, Title);
    const dataForGraph = {
        labels: keysArray,
        datasets: [{
            data: valuesArray,
            label: "",
            backgroundColor: "hsl(190, 70%, 70%)",
            hoverBackgroundColor: "hsl(190, 100%, 20%)",}]
    }

    const options = {

    }

    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', maxWidth: '650px', margin: '0 auto' }}>
            <h2 style={{ color: 'hsl(190, 100%, 20%)' }}>Training Frequency By Week</h2>
            <div style={{ width: '100%' }}>
                <Bar data={dataForGraph} options={options} />
            </div>
        </div>
    );


}
export default TrainingFrequencyByWeek;


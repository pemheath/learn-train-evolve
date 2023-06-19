import React, {useEffect} from "react";
import {useState} from 'react';
import {Chart as ChartJS, BarElement, CategoryScale, LinearScale, Title, Legend} from "chart.js";
import {Bar} from "react-chartjs-2";

const TrainingFrequencyByWeek = ({data}) => {
const [keysArray, setKeysArray] = useState([]);
const [valuesArray, setValuesArray] = useState([]);

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

    ChartJS.register(BarElement, CategoryScale, LinearScale, Title, Legend);
    const dataForGraph = {
        labels: keysArray,
        datasets: [{data: valuesArray
        , label: "Training Frequency", backgroundColor: "rgb(255, 99, 132)", borderWidth: 1, borderColor: "rgb(255, 99, 132)",}]
    }

    const options = {

    }



    return(<div>
        <h2>Training Frequency By Week</h2>
        <div style={{ maxWidth: "650px" }}>
            <Bar
            data={dataForGraph}
            options={options}
            >
            </Bar>

        </div>
    </div>);
}
export default TrainingFrequencyByWeek;


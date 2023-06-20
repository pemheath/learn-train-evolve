import React from 'react';
import ReactDOM from 'react-dom/client';

import {Amplify} from "aws-amplify";
import '@aws-amplify/ui-react/styles.css'
import awsExports from "./aws-exports";


import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import "./styles.css";

import App from "./App";
import ErrorPage from "./error-page";
import ListOfUserTrainingSessions from "./components/ListOfUserTrainingSessions"
import TrainComponent from "./components/TrainComponent";
import {ThemeProvider} from "@aws-amplify/ui-react";
import LogTrainingSessionForm from "./components/LogTrainingSessionForm";


Amplify.configure(awsExports);



const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        errorElement: <ErrorPage/>,

    },
    {
        path: "/train/:email",
        element:<TrainComponent/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/train/:email/signup/:eventId",
        element:<LogTrainingSessionForm/>,
        errorElement: <ErrorPage/>,
    },


]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <ThemeProvider>
            <RouterProvider router={router} />
        </ThemeProvider>
    </React.StrictMode>
);

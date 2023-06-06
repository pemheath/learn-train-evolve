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
import TrainingSessions from "./components/TrainingSessions";
import Goals from "./components/Goals"
import UserTrainingSession from "./components/UserTrainingSession";

Amplify.configure(awsExports);



const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        errorElement: <ErrorPage/>,

    },
    {
        path: "/train",
        element:<UserTrainingSession/>,
        errorElement: <ErrorPage/>,
    }

]);

ReactDOM.createRoot(document.getElementById("root")).render(
        <React.StrictMode>
            <RouterProvider router={router} />
        </React.StrictMode>
);

import React from "react";
import '../styles.css';

import brownBelt from '../project images/brownBelt.png'

import {useTheme, View} from "@aws-amplify/ui-react"


function Header() {

    const containerStyle = {
        position: 'relative',
        backgroundColor: "hsl(210, 10%, 58%)",
        backgroundSize: "contain",
        backgroundPosition: "center",
        height: "300px",
        backgroundImage: `url(${brownBelt})`,
    };

    const textStyle = {
        position: 'absolute',
        top: "50%",
        left: "50%",
        transform:  "translate(-50%, -50%)",
        color: "white",
        fontSize: "24px",
    };


    const {tokens} = useTheme();



    return (
        <div style={containerStyle}>
            <div style={textStyle}></div>
        </div>
    );
}

Header.displayName="Header";

export default Header;
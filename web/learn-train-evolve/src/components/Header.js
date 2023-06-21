import React from "react";
import '../styles.css';

import whiteBelt from '../project images/whiteBelt.png'

import {useTheme} from "@aws-amplify/ui-react"


function Header( ) {

    const containerStyle = {
        position: 'relative',
        backgroundColor: "hsl(190, 95%, 30%)",
        backgroundSize: "contain",
        backgroundPosition: "center",
        height: "200px",
        backgroundImage: `url(${whiteBelt})`,
    };

    const textStyle = {
        position: 'absolute',
        top: "50%",
        left: "50%",
        transform:  "translate(-50%, -50%)",
        color: "white",
        fontSize: "24px",
    };

    return (
        <div style={containerStyle}>
            <div style={textStyle}></div>
        </div>
    );
}

Header.displayName="Header";

export default Header;
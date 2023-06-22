import React from "react";
import '../styles.css';

import Black_belt from '../project images/Black_belt.svg'
import ltelogo from '../project images/ltelogo.png'

import {Card, Grid, useTheme, View} from "@aws-amplify/ui-react"


function Header( ) {

    const {tokens} = useTheme();

    const containerStyle = {
        position: 'relative',
        backgroundColor: tokens.colors.background.primary,
        height: "200px",

    };

    const textStyle = {
        position: 'center',
        top: "50%",
        left: "50%",
        transform:  "translate(-50%, -50%)",
        color: "white",
        fontSize: "24px",
    };
    //
    // return (
    //     <div style={containerStyle}>
    //         <div style={textStyle}></div>
    //     </div>
    // );
    return(
        <div>
            <Grid
                style={containerStyle}
            templateColumns="5fr 2fr 5fr"
                templateRows="3fr, 1fr"
            >
                <div
                    style={{
                        // backgroundImage: `url(${Black_belt})`,
                        backgroundRepeat: "no-repeat",
                        backgroundSize: "contain",
                        columns: "1"
                        }}

                >
                </div>
                <div
                    style={{
                        backgroundImage: `url(${ltelogo})`,
                        backgroundRepeat: "no-repeat",
                        backgroundSize: "contain",
                        columns: "2"
                    }}>
                </div>
                <div
                    style={{
                        // backgroundImage: `url(${Black_belt})`,
                        backgroundRepeat: "no-repeat",
                        backgroundSize: "contain",
                        columns: "3"
                    }}
                ></div>
                
            </Grid>
         </div>
    );
}

Header.displayName="Header";

export default Header;
import React from "react";
import '../styles.css';
import ltelogo from '../project images/ltelogo.png'

import {Grid, useTheme} from "@aws-amplify/ui-react"


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

    return(
        <div>
            <Grid
                style={containerStyle}
            templateColumns="5fr 2fr 5fr"
                templateRows="3fr, 1fr"
            >
                <div
                    style={{
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
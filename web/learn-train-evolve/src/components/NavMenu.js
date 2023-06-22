import React from 'react';
import {ButtonGroup, Button, useTheme, Card} from "@aws-amplify/ui-react";
import {useNavigate} from "react-router-dom";
import ltelogo from '../project images/ltelogo.png';
import whiteBelt from "../project images/whiteBelt.png";
const NavMenu = ({email}) => {

    const{tokens} = useTheme();

    const navigate = useNavigate();

    const handleClick = () => {
        alert("Feature under development!");
    }

    const goToTrain = () => {
        navigate(`/train/${email}`, {state: {email: email}});
        console.log("clicked");
    }
    const logoStyle = {
        position: 'relative',
        backgroundColor: "white",
        backgroundSize: "contain",
        backgroundPosition: "center",
        height: "200px",
        backgroundImage: `url(${ltelogo})`,
        backgroundRepeat: "no-repeat",
    };
    return (
        <div>
            <Card style={logoStyle}></Card>
            <ButtonGroup direction="column" variation="primary" justifyContent="start" size="large">
                <Button onClick={handleClick}>Learn:<br></br>View Resources</Button>
                <Button onClick={goToTrain}>Train:<br></br>Manage Sessions</Button>
                <Button onClick={handleClick}>Evolve:<br></br>Go to goals</Button>
            </ButtonGroup>
        </div>
    );
}

export default NavMenu;
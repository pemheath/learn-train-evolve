import React, {useState} from 'react';
import {
    SliderField,
    TextAreaField,
    ToggleButton,
    ToggleButtonGroup,
    Button,
    Card,
    useTheme,
    View, Heading, Flex,
} from "@aws-amplify/ui-react";
import axios from "axios";
import { ImFrustrated, ImSad, ImNeutral, ImSmile,  ImHappy  } from "react-icons/im";
import TagSelector from "./TagSelector";
import GoalSelector from "./GoalSelector";
import UpdatedUserTrainingSession from "./UpdatedUserTrainingSession";
import Footer from "./Footer";
import Header from "./Header";
import {useLocation, Link} from "react-router-dom";
import UserTrainingSession from "./UserTrainingSession";
import ltelogo from "../project images/ltelogo.png";



const LogTrainingSessionForm= ()=> {

    const location = useLocation();

    const {tokens} = useTheme();
    const [showForm, setShowForm] = useState(true);
    const[showData, setShowData] = useState(false);
    const[intensityRating, setIntensityRating] = useState(0);
    const[techniqueEnjoyment, setTechniqueEnjoyment] = useState(0);
    const[performanceRating, setPerformanceRating] = useState("3");
    const[note, setNote] = useState("");
    const[selectedTags, setSelectedTags] = useState([]);
    const[goal, setGoal] = useState("none");
    const[userTrainingSession, setUserTrainingSession] = useState(location.state.userTrainingSession);

    const linkStyle = {
        margin: "1rem",
        textDecoration: "none",
        border:  "1px solid hsl(190, 50%, 50%)",
        display: "inline-block",
        padding: "0.5rem",
        backgroundColor: "white",
        fontWeight: "bold",
        color: "hsl(190, 50%, 50%)",
        borderRadius: "0.5rem",
        hoverBackgroundColor: "hsl(190, 75%, 95%)",
        hoverColor: "white",
    };





    const handleTagToggle = (tag) => {
        console.log("in handleTagToggle");
        console.log("tag is: " + tag);
        console.log("before doing anything, selectedTags are: " + selectedTags);

        if (selectedTags.includes(tag)) {
            setSelectedTags(selectedTags.filter((t) => t !== tag));
        } else {
            const newTags = [...selectedTags, tag];
            setSelectedTags(newTags);
            console.log("now, selected Tags are" + selectedTags);
        }
    };

    async function handleSubmit(e) {
        e.preventDefault();
        setShowForm(false);
        const tagsToSubmit = selectedTags;
        if (tagsToSubmit.length === 0) {
            tagsToSubmit.push("none");
        }
        console.log("performance rating is:" + performanceRating)
        try {
            const api = axios.create({
                baseURL: `${process.env.REACT_APP_API_BASE_URL}`
            })

            let email = userTrainingSession.email;
            let eventId = userTrainingSession.eventId;
            console.log("calling put with tags" + tagsToSubmit);
            const result = await api.put(`/user-training-sessions/${email}/${eventId}`,
                {//from props
                    timeAndDate: userTrainingSession.timeAndDate, //from props
                    type: userTrainingSession.type, //from props
                    coach: userTrainingSession.coach, //from props
                    intensityRating: intensityRating, //user input
                    techniqueEnjoyment: techniqueEnjoyment, //user input
                    performanceRating: performanceRating, //user input
                    note: note, //not adding this functionality yet
                    goal: goal, //not adding this functionality yet
                    tags: tagsToSubmit, //user input
                    attended: true,

                });
            setUserTrainingSession(result.data.userTrainingSession);
            setShowData(true);
        } catch (error) {
            console.log("error updating user training session", error);
        }
    }

    const logoStyle = {
        display: 'flex',
        position: 'start',
        backgroundColor: "white",
        backgroundSize: "contain",
        backgroundPosition: "left",
        height: "200px",
        backgroundImage: `url(${ltelogo})`,
        backgroundRepeat: "no-repeat",
    };


    return (
        <div>
            <Header/>
            <Card style={logoStyle}></Card>
            <UserTrainingSession
            userTrainingSession={userTrainingSession}
            />
            <Heading level={4} color={tokens.colors.brand.primary[100]} textAlign={"center"}> Log your Training </Heading>
            <Flex>
                <Link style={linkStyle} to ={`../train/${userTrainingSession.email}`} state={{ email: userTrainingSession.email}}>Back to My Training</Link>
                <Link to={".."} style={linkStyle}>Return Home</Link>
            </Flex>
        <form>
            {showForm &&
            <View
                position="relative"
            >
            <Card
                variation="elevated"
                display="inline-block"
            >
            <ToggleButtonGroup
                isExclusive
                value={performanceRating}
                onChange={(value) => setPerformanceRating(value[0])}
                variation="menu"
            >How was your performance today?
                <ToggleButton value="1">
                    <ImFrustrated/>
                </ToggleButton>
                <ToggleButton value="2" >
                    <ImSad/>
                </ToggleButton>
                <ToggleButton value="3">
                    <ImNeutral/>
                </ToggleButton>
                <ToggleButton value="4">
                    <ImSmile/>
                </ToggleButton>
                <ToggleButton value="5">
                    <ImHappy/>
                </ToggleButton>
            </ToggleButtonGroup>

            <SliderField
                label="Rate your enjoyment of today's technique."
                descriptiveText="Hate it, love it?"
                filledTrackColor={tokens.colors.brand.secondary[80]}
                min={1}
                max={7}
                step={1}
                value={techniqueEnjoyment}
                onChange={setTechniqueEnjoyment}
            />
            <SliderField
                label="How intense was your training?"
                descriptiveText="Chill day of drilling, or all out competition class?"
                filledTrackColor={tokens.colors.brand.secondary[80]}
                min={1}
                max={100}
                value={intensityRating}
                onChange={setIntensityRating}
            />
            <View>
                <TagSelector
                    tags={["come up sweep", "guard retention", "single leg takedown", "pressure passing", "submission defense", "back control", "conditioning", "mindset"]}
                    selectedTags={selectedTags}
                    key={selectedTags}
                    onSelect={(word)=> handleTagToggle(word)}
                />

                <Flex selectedTags={selectedTags}
                >
                    {selectedTags.map((tag) => (
                        <Card
                            backgroundColor={tokens.colors.teal["20"]}
                            key={tag}
                            tag={tag}
                            value={tag}
                        >{tag}
                        </Card>
                    ))}
                </Flex>
            </View>
            <TextAreaField
                label="Notes from today"
                value={note}
                onChange={(e)=>setNote(e.target.value)}
            ></TextAreaField >
                <GoalSelector
                    onChange={(e)=>setGoal(e.target.value)}
                    goals={["Not today", "Train 4 days in one week", "Manage frustration while rolling with Taylor", "Defend the berimbolo"]}
                              ></GoalSelector>
            <Button onClick={handleSubmit} size="large"> Submit</Button>
            </Card>
            </View>}

            {showData &&
            <Card>
                <UpdatedUserTrainingSession
                userTrainingSession={userTrainingSession}
                />
            </Card>
            }
        </form>
            <Footer/>

        </div>


    );
}

LogTrainingSessionForm.displayName="LogTrainingSessionForm";

export default LogTrainingSessionForm;
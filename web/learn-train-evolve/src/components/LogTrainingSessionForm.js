import React, {useState} from 'react';
import {
    SliderField,
    TextAreaField,
    ToggleButton,
    ToggleButtonGroup,
    Button,
    Card,
    useTheme,
    View, Heading, Collection,
} from "@aws-amplify/ui-react";
import axios from "axios";
import { ImFrustrated, ImSad, ImNeutral, ImSmile,  ImHappy  } from "react-icons/im";
import TagSelector from "./TagSelector";
import GoalSelector from "./GoalSelector";
import UpdatedUserTrainingSession from "./UpdatedUserTrainingSession";
import Footer from "./Footer";
import Header from "./Header";
import {useLocation, Link} from "react-router-dom";



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





    const handleTagToggle = (tag) => {
        console.log("in handleTagToggle");
        console.log("tag is: " + tag);
        console.log("before doing anything, selectedTags are: " + selectedTags);

        if (selectedTags.includes(tag)) {
            setSelectedTags(selectedTags.filter((t) => t !== tag));
        } else {
            selectedTags.push(tag)
            setSelectedTags(selectedTags);
            console.log("now, selected Tags are" + selectedTags);
        }
    };

    async function handleSubmit(e) {
        e.preventDefault();
        setShowForm(false);
        const tagsToSubmit = selectedTags.size === 0 ? new Set(['none']) : selectedTags;
        console.log("performance rating is:" + performanceRating)
        try {
            const api = axios.create({
                baseURL: `${process.env.REACT_APP_API_BASE_URL}`
            })

            let email = userTrainingSession.email;
            let eventId = userTrainingSession.eventId;
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

    return (
        <div>
            <Header/>
            <Link to ={`../train/${userTrainingSession.email}`} state={{userTrainingSession: userTrainingSession, email: userTrainingSession.email}}>Back to My Training</Link>
            <Link to={".."}>Return Home</Link>
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
            <Card>
                <TagSelector
                    tags={["come up sweep", "guard retention", "single leg takedown", "pressure passing", "submission defense", "back control", "conditioning", "mindset"]}
                    selectedTags={selectedTags}
                    key={selectedTags}
                    onSelect={(word)=> handleTagToggle(word)}
                />

                {selectedTags &&
                    <div>
                        <Heading level={6}>Tags you are adding</Heading>
                        <Collection
                            type = "list"
                            backgroundColor={tokens.colors.white}
                            items={selectedTags}
                            gap = "1.rem"
                        >
                            {(item, index) => (
                                <Card
                                    key={index}
                                    backgroundColor={tokens.colors.brand.primary[20]}
                                    padding={tokens.space.medium}
                                >{item}
                                </Card>
                            )}
                        </Collection> </div>}
            </Card>
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
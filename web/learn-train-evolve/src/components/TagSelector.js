import React, {useEffect, useState} from 'react';
import {
    Card,
    TextField,
    Button,
    Heading,
    ToggleButton,
    Text,
    useTheme,
    Flex, View,
} from "@aws-amplify/ui-react";
import {ImPlus, ImPriceTags} from "react-icons/im";
import App from "../App";

const TagSelector = ({ tags, selectedTags, onSelect }) => {
    const[tagsToDisplay, setTagsToDisplay] = useState(tags);
    const[newTag, setNewTag] = useState('');
    const {tokens} = useTheme();
    const [showMessage ,setShowMessage] = useState(false)

    useEffect(() => {
        let timeoutId;
        if (showMessage) {
            timeoutId = setTimeout(() => {
                setShowMessage(false);
                // Reset the form here
            }, 2000);
        }
        return () => {
            clearTimeout(timeoutId);
        };
    }, [showMessage]);


    const handleClick= (word) => {
        if (tagsToDisplay.includes(word)) {
            setNewTag('');
            alert("Tag already exists.")
        }
        else{
            setShowMessage(true);
            selectedTags.push(word);
            tagsToDisplay.push(word);
            onSelect(word);
            setTagsToDisplay(tagsToDisplay);}
    }


    return (
        <View>
            <Heading
            textAlign="center"
            ><ImPriceTags
            />What did you work on?
                <ImPriceTags
            /></Heading>
            <Flex
            >
            {tagsToDisplay.map((tag) => (
                <ToggleButton
                    key={tag}
                    tag={tag}
                    value={tag}
                    isPressed={selectedTags.includes(tag)}
                    onChange={onSelect}
                    isExclusive={false}
                >{tag}
                </ToggleButton>
            ))}
            </Flex>
            <Card
                variation="elevated"
                backgroundColor={tokens.colors.neutral[20]}
            >
            <form>
                <Text
                variation="info"
                >
                    You may enter additional tags.
                </Text>
                <TextField
                    label="Tag"
                    value={newTag} onChange={e => setNewTag(e.target.value)} placeholder="Optinal tag 1"/>
                <Button
                    variation="primary"
                    onClick={() => handleClick(newTag)}
                ><ImPlus/> Add Tags
                </Button>
                    {showMessage && <div>
                        <View
                            backgroundColor={tokens.colors.green[20]}
                        > New tag Successfully added!
                        </View>
                    </div>}
            </form>
            </Card>
        </View>
    );
};

TagSelector.displayName="TagSelector";

export default TagSelector;














import React, { useState } from 'react';
import {
    Card,
    TextField,
    Button,
    Heading,
    ToggleButton,
    Text,
    useTheme,
    Flex
} from "@aws-amplify/ui-react";
import {ImPlus, ImPriceTags} from "react-icons/im";

const TagSelector = ({ tags }) => {
    const[tagsToDisplay, setTagsToDisplay] = useState(tags);
    const [selectedTags, setSelectedTags] = useState([]);
    console.log(selectedTags);
    const[newTag, setNewTag] = useState('');
    const[secondNewTag, setSecondNewTag] = useState('');


    const {tokens} = useTheme();

    const handleTagToggle = (tag) => {

        if (selectedTags.includes(tag)) {
            setSelectedTags(selectedTags.filter((t) => t !== tag));
        } else {
            setSelectedTags([...selectedTags, tag]);
        }
        console.log(selectedTags);
    };

    function handleTextAreaChange(e) {
        setNewTag(e.target.value);
    }

    const addTag= (word) => {
        selectedTags.push(word);
        setSelectedTags(selectedTags);
        tagsToDisplay.push(word);

    }
    return (
        <div>
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
                    onChange={handleTagToggle}
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
                    You may enter up to two additional tags. These will be saved to your profile.
                </Text>
                <label htmlFor="tag1">Tag 1:</label>
                <TextField value={newTag} onChange={handleTextAreaChange}  placeholder="Enter tag 1"/>
                <Button
                    variation="primary"
                    onClick={() => addTag(newTag)}
                >
                    <ImPlus/> Add Tags
                </Button>
                <label htmlFor="tag2">Tag 2:</label>
                <TextField value={secondNewTag} onChange={handleTextAreaChange} placeholder="Enter tag 2"/>
                    <Button
                        variation="primary"
                        onClick={() => addTag(secondNewTag)}
                    >
                        <ImPlus/> Add Tags
                    </Button>
            </form>
            </Card>
        </div>
    );
};

export default TagSelector;
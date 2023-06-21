import React, {useState} from 'react';
import {
    Card,
    TextField,
    Button,
    Heading,
    ToggleButton,
    Text,
    useTheme,
    Flex, View, Alert,
} from "@aws-amplify/ui-react";
import {ImPlus, ImPriceTags} from "react-icons/im";

/**
 * A component for the user to select tags when logging training
 *  tags: (currently provided, eventually most frequently used tags for the user)
 *  selectedTags: the list of tags to include when updating the user training session
 *  onSelect: the method for handling tag selection and adding tags to the selected list
 */
const TagSelector = ({ tags, selectedTags, onSelect }) => {


    const[tagsToDisplay, setTagsToDisplay] = useState(tags);
    const[newTag, setNewTag] = useState('');
    const {tokens} = useTheme();
    const [showMessage ,setShowMessage] = useState(false);




    const handleClick = () => {
        const word = newTag;
        if (!tagsToDisplay.includes(word)) {
            setShowMessage(true);
            const newTags = [...tagsToDisplay, word];
            setTagsToDisplay(newTags);
        }
        onSelect(word);
    };


return (
        <View>
            <Heading
            textAlign="center"
            ><ImPriceTags
            />What did you work on?
                <ImPriceTags
            /></Heading>
            <Flex tagsToDisplay={tagsToDisplay}>
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
                    value={newTag}
                    onChange={e => setNewTag(e.target.value)} placeholder="Optinal additional tag"/>
                <Button
                    variation="primary"
                    onClick={handleClick}
                ><ImPlus/> Add Tags
                </Button>
                    {showMessage && <div>
                        <Alert isDismissible={true} variation="success" heading="Success!">
                            New Tag Successfully Added!
                        </Alert>
                    </div>}
            </form>
            </Card>
        </View>
    );
};

TagSelector.displayName="TagSelector";

export default TagSelector;














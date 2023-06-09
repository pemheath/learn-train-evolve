import React, {useEffect, useState} from 'react';
import {
    Card,
    TextField,
    Button,
    Heading,
    ToggleButton,
    Text,
    useTheme,
    Flex, View, Collection,
} from "@aws-amplify/ui-react";
import {ImPlus, ImPriceTags} from "react-icons/im";

const TagSelector = ({ tags }) => {
    const[tagsToDisplay, setTagsToDisplay] = useState(tags);
    const [selectedTags, setSelectedTags] = useState([]);
    const[newTag, setNewTag] = useState('');
    const {tokens} = useTheme();
    const [showMessage ,setShowMessage] = useState(false)


    const handleTagToggle = (tag) => {

        if (selectedTags.includes(tag)) {
            setSelectedTags(selectedTags.filter((t) => t !== tag));
        } else {
            setSelectedTags([...selectedTags, tag]);
        }
    };

    useEffect(() => {
        let timeoutId;
        if (showMessage) {
            timeoutId = setTimeout(() => {
                setShowMessage(false);
                // Reset the form here
            }, 3000);
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
            setSelectedTags(selectedTags);
            tagsToDisplay.push(word);
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
                            selectedTag={item}
                            backgroundColor={tokens.colors.brand.primary[20]}
                            padding={tokens.space.medium}
                        >{item}
                        </Card>
                    )}
            </Collection> </div>}
        </View>
    );
};

export default TagSelector;














import React from "react";
import {
    Card,
    Image,
    View,
    Heading,
    Flex,
    Text,
    Button,
    useTheme,
} from '@aws-amplify/ui-react';

 const Pillar = () => {
    const { tokens } = useTheme();
    return (
        <View
            backgroundColor={tokens.colors.background.secondary}
            padding={tokens.space.medium}
        >
            <Card>
                <Flex direction="row" alignItems="flex-start">
                    <Image
                        src="https://images.pexels.com/photos/1917328/pexels-photo-1917328.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260./images/pexels-ravi-kant-1917328"
                        width="20%"
                        objectFit="initial"
                        alt="Climing"/>
                    <Flex
                        direction="column"
                        alignItems="flex-start"
                        gap={tokens.space.xs}
                    >
                        <Heading level={5}>
                            Training Sessions for This Week
                        </Heading>

                        <Text as="span">
                            What commitments will you make?
                        </Text>
                        <Button variation="primary">Book it</Button>
                    </Flex>
                </Flex>
            </Card>
        </View>
    );
};

 export default Pillar;
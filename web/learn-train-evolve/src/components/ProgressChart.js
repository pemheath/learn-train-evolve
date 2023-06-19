import React, {useEffect} from "react";
import  {useTheme} from "@aws-amplify/ui-react";
import axios from "axios";
import {VictoryPie, VictoryLabel} from "victory";


const ProgressChart = ({totalSessions}) => {

    const labelRadius = 60;

        if(totalSessions === 0) {
            return <div>Loading...</div>
        }

        return (
            <div style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <div style={{ width: '80%', maxWidth: '400px', position: 'relative',}}>
                        <VictoryPie
                            data={[
                                { x: "Sessions Trained", y: totalSessions },
                                { x: "Sessions Remaining", y: 100 - totalSessions }
                            ]}
                            animate={{ duration: 3000 }}
                            width={400}
                            height={400}
                            innerRadius={100}
                            cornerRadius={13}
                            labels={() => null}
                            colorScale={["green", "red"]}
                            labelRadius={({ innerRadius }) => innerRadius + labelRadius}
                        />

                </div>
            </div>
        );

}

ProgressChart.displayName="ProgressChart";

export default ProgressChart;
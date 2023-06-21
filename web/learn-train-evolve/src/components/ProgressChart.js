import React from "react";

import {VictoryPie, VictoryLabel} from "victory";
import background2 from "../project images/background2.jpeg";


const ProgressChart = ({totalSessions}) => {

    const labelRadius = 60;

    if(totalSessions===0) {
        return  <div style={ { backgroundSize: "contain", backgroundRepeat: "no-repeat", backgroundImage: `url(${background2})`, width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}></div>;
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
                            colorScale={["var(--amplify-colors-teal-40)", "var(--amplify-colors-teal-90)"]}
                            labelRadius={({ innerRadius }) => innerRadius + labelRadius}
                        />
                    <VictoryLabel
                        textAnchor="middle"
                        verticalAnchor="middle"
                        x={400}
                        y={400}
                        text={`${totalSessions}% to 100!`}/>

                </div>
            </div>
        );

}

ProgressChart.displayName="ProgressChart";

export default ProgressChart;
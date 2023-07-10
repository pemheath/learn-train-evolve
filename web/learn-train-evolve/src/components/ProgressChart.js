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
                <div style={{ width: '80%', maxWidth: '400px', position: 'relative'}}>
                    <h2 style={{ color: 'hsl(190, 100%, 20%)', textAlign: 'center'}}>Toward 100 classes</h2>
                        <VictoryPie
                            data={[
                                { x: "Sessions Trained", y: totalSessions },
                                { x: "Sessions Remaining", y: 100 - totalSessions }
                            ]}
                            width={400}
                            height={400}
                            innerRadius={100}
                            cornerRadius={13}
                            labels={() => null}
                            colorScale={["var(--amplify-colors-teal-40)", "var(--amplify-colors-teal-90)"]}
                            labelRadius={({ innerRadius }) => innerRadius + labelRadius}
                        />
                    <div style={{ position: 'absolute', top: '58%', left: '50%', transform: 'translate(-50%, -50%)', textAlign: 'center'}}>
                        <h2 style={{color:  "var(--amplify-colors-teal-100)" }}>{totalSessions}%</h2>
                    </div>

                </div>
            </div>
        );

}

ProgressChart.displayName="ProgressChart";

export default ProgressChart;
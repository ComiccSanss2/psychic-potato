/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.codingdojo.students;


import fr.rphstudio.codingdojo.game.Pod;
import fr.rphstudio.codingdojo.game.PodPlugIn;

import javax.swing.*;

/**
 *
 * @author Romuald GRIGNON
 */
public class Student11 extends PodPlugIn {

    public Student11(Pod p) {
        super(p);
    }

    //-------------------------------------------------------
    // DECLARE YOUR OWN VARIABLES AND FUNCTIONS HERE

    boolean mustCharge = false;

    // END OF VARIABLES/FUNCTIONS AREA
    //-------------------------------------------------------

    void moveToFirstChargingCheckPoint2(float speed) {
        moveToFirstChargingCheckPoint2();
    }


    void moveToNextCheckPoint2(float speed) {
        moveToNextCheckPoint2();
        accelerateOrBrake(speed * 0.82f);

    }

    void moveToNextCheckPoint2() {
        int index = getNextCheckPointIndex();
        float x = getCheckPointX(index);
        float y = getCheckPointY(index);
        turnTowardPosition2(x, y);
        if (getShipBoostLevel() == 100) {

            float distance = getDistanceBetweenPositions(getShipPositionX(), getShipPositionY(), x, y);

            // et angle ship environ equivalent a angle destination
            float angleDest = getAbsoluteAngleFromPositions(getShipPositionX(), getShipPositionY(), x, y);
            float angleShip = getShipAngle();

            float angleDifference = getRelativeAngleDifference(angleDest, angleShip);

            if (abs(angleDifference) < 12.5f && distance < 13) {
                useBoost();

            }

        }
}

    void moveToFirstChargingCheckPoint2() {
        int index = getFirstChargingCheckPointIndex();
        float x = getCheckPointX(index);
        float y = getCheckPointY(index);

        float dist = getDistanceBetweenPositions(getShipPositionX(), getShipPositionY(), x, y);
        if (dist <= 0.50) {
            accelerateOrBrake(-1f);
        } else if (dist <= 2) {
            accelerateOrBrake(0.35f);
        } else {
            accelerateOrBrake(0.35f);
        }

        turnTowardPosition2(x, y);

    }

    public int getFirstChargingCheckPointIndex() {
        for (int i = 0; getNbRaceCheckPoints() > i; i++) {
            if (isCheckPointCharging(i) == true) {
                return i;
            }
        }
        return -1;
    }

    boolean updateChargingMode2(float minBatLevel, float maxBatLevel) {
        float LevelBattery = getShipBatteryLevel();
        if (LevelBattery <= minBatLevel) {
            mustCharge = true;
        } else if (LevelBattery >= maxBatLevel) {
            mustCharge = false;
        } else {
        }
        return mustCharge;
    }

    void moveAndRecharge2(float speed, float minBatLevel, float maxBatLevel) {
        boolean mustCharge = updateChargingMode2(minBatLevel, maxBatLevel);
        if (mustCharge == true) {
            // si je dois charger
            moveToFirstChargingCheckPoint2(speed);
        } else {
            // si je continue
            moveToNextCheckPoint2(speed);
        }

    }

    void turnTowardPosition2(float cx, float cy) {
        float sx = getShipPositionX();
        float sy = getShipPositionY();
        float angle = getAbsoluteAngleFromPositions(sx, sy, cx, cy);
        turnToAngle2(angle);
    }

    void turnToAngle2(float AngleDest){
        float Sangle = getShipAngle();
        float AngleDiff= getRelativeAngleDifference(Sangle , AngleDest);
        turn(AngleDiff);

    }


    @Override
    public void process(int delta)

    {
        //-------------------------------------------------------
        // WRITE YOUR OWN CODE HERE

        setPlayerName("jaileseum");
        selectShip(21);
        setPlayerColor(197, 67, 229, 288);

        // moveAndRecharge(0.75f, 20, 85);
        moveAndRecharge2(0.82f, 20, 80);

        // END OF CODE AREA
        //-------------------------------------------------------
    }

}

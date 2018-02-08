### Part 1 [6 marks]

## Part 1.1

# Build a method that keeps the robot a configurable distance away from an obstacle in front of it.

* MUST use proportional feedback control to influence the robot's speed so the further the robot is away from the obstacle, the faster it moves initially

* DO NOT use third-party implementations of feedback controllers

## Part 1.2

# Once you have done this, build a robot that can follow a dark line on a light background

* Try your approach on different lines (straight, curved or a mix) and try to determine how and where it breaks.

* _The demonstrators will run a competition to see which is the fastest robot on one of these lines._

* Make sure it works well on a range of lines, and perform quantitative testing on the more challenging ones (e.g. telling us it worked 7 times out of 10).

* Note that light sensors are susceptible to changes to ambient light level and also the condition of the surface being pointed at. __1 mark will be awarded__ for making sure your system is robust against being started in different conditions.

# For the demo and code, please include your solutions for the following problems, as described above:

1. Proportional feedback control for staying a configurable distance away from an obstacle
2. A line following approach
3. An approach to making your line following approach robust to different environmental conditions

### Part 2 [8 marks]

## Part 2.1

* Extend your line following robot to detect line junctions, i.e. where one line crosses another on a grid.

_You can enable junction detection by adding additional sensors, or by writing code to get your existing sensors to detect the presence of junctions. You may want to use the Subsumption architecture here, with different behaviours for line following, and junction detection selection._

## Part 2.2

* Add the ability to change to a different line randomly at a junction

## Part 2.3

* Make it such that your robot can be scripted to take an arbitrary path across a grid

# For the demo and code, please include your solutions for the following problems, as described above:

1. A robot that can follow lines on a grid, and is able to detect junctions
2. An extension to this that can change to a randomly select line at a junction
3. An extension to this where a user can specify


### Part 3 [8 marks]

## Part 3.1
* Build a controller so that your robot can follow a brightly coloured object, such as a ball, or a light source such as a torch, using the NXTCam.

## Part 3.2
Try extending your system so that it avoids a ball of another colour, or areas of low illumination.

_Using the approaches you've already tried, can you build a conga line of robots?_

# For the demo and code, please include your solutions for the following problems, as described above:

1. A robot that can follow a coloured object or bright light.

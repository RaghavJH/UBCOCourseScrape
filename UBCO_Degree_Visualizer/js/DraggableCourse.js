/*
  > !! This class requires the course.js, ubcoCourse.js, p5.js, addons/p5.dom.min.js scripts.
  > !! This class also requies the UbcoCourses objects to be brought in as an argument.
  > !! This file must reside in the same folder as sample.html in order for the import to be successful.
  > This function contains the code to construct the box and checks for it to be draggable

  INSTRUCTIONS FOR SAMPLE.HTML FILE:
    1) Import this file by typing the following element:	<script type="text/javascript" src="DraggableCourse.js"></script>
    2) In the setup function, create the canvas space for all the objects to reside on (i.e. canvas(1542,942);).
    3) Make sure you create the array of this object type before the setup and draw function so that it can be accessed anywhere (i.e. var Courses = [];).
    4) In the setup function, add the necessary number of objects to the array.
    5) In the draw function, call all the necessary functions of the objects so that everything is refreshed at 60Hz.
*/

    function DraggableCourse(course){
      this.course = course;
      this.w = 150;
      this.h = 40;
      this.x = 500 * Math.random() + (150/2);
      this.y = 500 * Math.random() + (40/2);
      this.offsetx = 0;
      this.offsety = 0;
      this.isHover = true;
      this.drawCourse = drawCourse;
      this.displayCourseDescription = displayCourseDescription;
      rectMode(CENTER);
      textAlign(CENTER, CENTER);
      textSize(20);
    }

    function drawCourse(){
      if(mouseX > this.x - this.w/2 + this.offsetx && mouseX < this.x + this.w/2 + this.offsetx && mouseY > this.y - this.h/2 + this.offsety && mouseY < this.y + this.h/2 + this.offsety){
        this.isHover = true;
      } else {
        this.isHover = false;
      }
      //if the box doesn't hover on the object, the box will be white
      if(!this.isHover){
         stroke(5);
         fill(255);
         rect(this.x + this.offsetx, this.y+this.offsety, this.w, this.h);
         fill(0);
         text(this.course.code + " " + this.course.section, this.x + this.offsetx, this.y + this.offsety);

        this.displayCourseDescription();

      } else {
        //if the mouse hovers on the object, the box will be gray.
        stroke(5);
        fill(200);
        rect(this.x + this.offsetx, this.y + this.offsety, this.w, this.h);
        fill(0);
        textSize(20);
        text(this.course.code + " " + this.course.section, this.x + this.offsetx, this.y + this.offsety);
      }
    }

    function displayCourseDescription(){
      var descriptionW = 200, descriptionH = 150;
      fill(255);
      rect(this.x,this.y+(this.h/2),descriptionW,descriptionH);
      fill(0);
      text(this.course.description, this.x + 2, this.y + 2);

    }

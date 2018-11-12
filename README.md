# Data extracted
Extracts:
- Course types by code and name (e.g. COSC - Computer Science, ANTH - Anthropology, etc.)
- Course sections (e.g. COSC 111, COSC 121, etc.)
- Credits
- Course title
- Course description
- Prerequisites
- Corequisites
- Equivalency
# Sample 
    <coursetype code="MUSC" fullname="Music">
        <course>
            <section>100</section>
            <credits>3</credits>
            <title>Principles of Musical Form</title>
            <description>Fundamental materials and processes of music - rhythmic, melodic, textural, ...</description>
            <prerequisite boolean="">None</prerequisite>
            <corequisite>None</corequisite>
            <equivalency>None</equivalency>
        </course>
        <course>
            <section>103</section>
            <credits>3</credits>
            <title>Introduction to the Theory of Music</title>
            <description>Concepts of rhythm, pitch, timbre, and texture; ...</description>
            <prerequisite boolean="">None</prerequisite>
            <corequisite>None</corequisite>
            <equivalency>None</equivalency>
        </course>
        ...
    </coursetype>
# TODO
Currently working on adding custom boolean expressions for prerequisites that make them computer readable. E.g. if a prerequisite is "One of Math 100, Math 101 or Math 200" it evaluates to "OR(MATH 100,MATH 101,MATH 200)"

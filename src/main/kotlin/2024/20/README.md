<article class="day-desc"><h2>--- Day 20: Race Condition ---</h2><p>The Historians are quite pixelated again. This time, a massive, black building looms over you - you're <a href="/2017/day/24">right outside</a> the CPU!</p>
<p>While The Historians get to work, a nearby program sees that you're idle and challenges you to a <em>race</em>. Apparently, you've arrived just in time for the frequently-held <em>race condition</em> festival!</p>
<p>The race takes place on a particularly long and twisting code path; programs compete to see who can finish in the <em>fewest picoseconds</em>. The <span title="If we give away enough mutexes, maybe someone will use one of them to fix the race condition!">winner</span> even gets their very own <a href="https://en.wikipedia.org/wiki/Lock_(computer_science)" target="_blank">mutex</a>!</p>
<p>They hand you a <em>map of the racetrack</em> (your puzzle input). For example:</p>
<pre><code>###############
#...#...#.....#
#.#.#.#.#.###.#
#<em>S</em>#...#.#.#...#
#######.#.#.###
#######.#.#...#
#######.#.###.#
###..<em>E</em>#...#...#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>The map consists of track (<code>.</code>) - including the <em>start</em> (<code>S</code>) and <em>end</em> (<code>E</code>) positions (both of which also count as track) - and <em>walls</em> (<code>#</code>).</p>
<p>When a program runs through the racetrack, it starts at the start position. Then, it is allowed to move up, down, left, or right; each such move takes <em>1 picosecond</em>. The goal is to reach the end position as quickly as possible. In this example racetrack, the fastest time is <code>84</code> picoseconds.</p>
<p>Because there is only a single path from the start to the end and the programs all go the same speed, the races used to be pretty boring. To make things more interesting, they introduced a new rule to the races: programs are allowed to <em>cheat</em>.</p>
<p>The rules for cheating are very strict. <em>Exactly once</em> during a race, a program may <em>disable collision</em> for up to <em>2 picoseconds</em>. This allows the program to <em>pass through walls</em> as if they were regular track. At the end of the cheat, the program must be back on normal track again; otherwise, it will receive a <a href="https://en.wikipedia.org/wiki/Segmentation_fault" target="_blank">segmentation fault</a> and get disqualified.</p>
<p>So, a program could complete the course in 72 picoseconds (saving <em>12 picoseconds</em>) by cheating for the two moves marked <code>1</code> and <code>2</code>:</p>
<pre><code>###############
#...#...12....#
#.#.#.#.#.###.#
#S#...#.#.#...#
#######.#.#.###
#######.#.#...#
#######.#.###.#
###..E#...#...#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>Or, a program could complete the course in 64 picoseconds (saving <em>20 picoseconds</em>) by cheating for the two moves marked <code>1</code> and <code>2</code>:</p>
<pre><code>###############
#...#...#.....#
#.#.#.#.#.###.#
#S#...#.#.#...#
#######.#.#.###
#######.#.#...#
#######.#.###.#
###..E#...12..#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>This cheat saves <em>38 picoseconds</em>:</p>
<pre><code>###############
#...#...#.....#
#.#.#.#.#.###.#
#S#...#.#.#...#
#######.#.#.###
#######.#.#...#
#######.#.###.#
###..E#...#...#
###.####1##.###
#...###.2.#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>This cheat saves <em>64 picoseconds</em> and takes the program directly to the end:</p>
<pre><code>###############
#...#...#.....#
#.#.#.#.#.###.#
#S#...#.#.#...#
#######.#.#.###
#######.#.#...#
#######.#.###.#
###..21...#...#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>Each cheat has a distinct <em>start position</em> (the position where the cheat is activated, just before the first move that is allowed to go through walls) and <em>end position</em>; cheats are uniquely identified by their start position and end position.</p>
<p>In this example, the total number of cheats (grouped by the amount of time they save) are as follows:</p>
<ul>
<li>There are 14 cheats that save 2 picoseconds.</li>
<li>There are 14 cheats that save 4 picoseconds.</li>
<li>There are 2 cheats that save 6 picoseconds.</li>
<li>There are 4 cheats that save 8 picoseconds.</li>
<li>There are 2 cheats that save 10 picoseconds.</li>
<li>There are 3 cheats that save 12 picoseconds.</li>
<li>There is one cheat that saves 20 picoseconds.</li>
<li>There is one cheat that saves 36 picoseconds.</li>
<li>There is one cheat that saves 38 picoseconds.</li>
<li>There is one cheat that saves 40 picoseconds.</li>
<li>There is one cheat that saves 64 picoseconds.</li>
</ul>
<p>You aren't sure what the conditions of the racetrack will be like, so to give yourself as many options as possible, you'll need a list of the best cheats. <em>How many cheats would save you at least 100 picoseconds?</em></p>
</article>
<article class="day-desc"><h2 id="part2">--- Part Two ---</h2><p>The programs seem perplexed by your list of cheats. Apparently, the two-picosecond cheating rule was deprecated several milliseconds ago! The latest version of the cheating rule permits a single cheat that instead lasts at most <em>20 picoseconds</em>.</p>
<p>Now, in addition to all the cheats that were possible in just two picoseconds, many more cheats are possible. This six-picosecond cheat saves <em>76 picoseconds</em>:</p>
<pre><code>###############
#...#...#.....#
#.#.#.#.#.###.#
#S#...#.#.#...#
#1#####.#.#.###
#2#####.#.#...#
#3#####.#.###.#
#456.E#...#...#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>Because this cheat has the same start and end positions as the one above, it's the <em>same cheat</em>, even though the path taken during the cheat is different:</p>
<pre><code>###############
#...#...#.....#
#.#.#.#.#.###.#
#S12..#.#.#...#
###3###.#.#.###
###4###.#.#...#
###5###.#.###.#
###6.E#...#...#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############
</code></pre>
<p>Cheats don't need to use all 20 picoseconds; cheats can last any amount of time up to and including 20 picoseconds (but can still only end when the program is on normal track). Any cheat time not used is lost; it can't be saved for another cheat later.</p>
<p>You'll still need a list of the best cheats, but now there are even more to choose between. Here are the quantities of cheats in this example that save <em>50 picoseconds or more</em>:</p>
<ul>
<li>There are 32 cheats that save 50 picoseconds.</li>
<li>There are 31 cheats that save 52 picoseconds.</li>
<li>There are 29 cheats that save 54 picoseconds.</li>
<li>There are 39 cheats that save 56 picoseconds.</li>
<li>There are 25 cheats that save 58 picoseconds.</li>
<li>There are 23 cheats that save 60 picoseconds.</li>
<li>There are 20 cheats that save 62 picoseconds.</li>
<li>There are 19 cheats that save 64 picoseconds.</li>
<li>There are 12 cheats that save 66 picoseconds.</li>
<li>There are 14 cheats that save 68 picoseconds.</li>
<li>There are 12 cheats that save 70 picoseconds.</li>
<li>There are 22 cheats that save 72 picoseconds.</li>
<li>There are 4 cheats that save 74 picoseconds.</li>
<li>There are 3 cheats that save 76 picoseconds.</li>
</ul>
<p>Find the best cheats using the updated cheating rules. <em>How many cheats would save you at least 100 picoseconds?</em></p>
</article>

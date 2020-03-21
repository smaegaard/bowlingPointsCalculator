package com.github.smaegaard.bowlingPointsCalculator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculate bowling points accordingly to this link
 * <p>
 * https://en.wikipedia.org/wiki/Ten-pin_bowling#Traditional_scoring
 *
 */
public class TraditionalScoreCalculator implements IScoreCalculator {
    ArrayList<Integer> result = new ArrayList<>();
    int frame;

    @Override
    public List<Integer> calculateScore(List<Integer> points) {
        result.clear();
        frame = 0;

        for (int i = 0; i < points.size(); i++) {

            if (getFrame() > 10) {
                break;
            }

            if (i % 2 == 0) {
                switch (getFrameState(points.get(i), points.get(i + 1))) {
                    case STRIKE:
                        if (points.size() <= i + 3) {
                            addScoreToResult(10);
                            break;
                        }
                        switch (getFrameState(points.get(i + 2), points.get(i + 3))) {
                            case STRIKE:
                                if (points.size() <= i + 5) {
                                    addScoreToResult(20);
                                    break;
                                }
                                switch (getFrameState(points.get(i + 4), points.get(i + 5))) {
                                    case STRIKE:
                                        addScoreToResult(30);
                                        break;
                                    case OPEN:
                                    case SPLIT:
                                    default:
                                        if (points.size() <= i + 4) {
                                            addScoreToResult(20);
                                            break;
                                        }
                                        addScoreToResult(20 + points.get(i + 4));
                                        break;
                                }
                                break;
                            case OPEN:
                            case SPLIT:
                            default:
                                if (points.size() <= i + 3) {
                                    addScoreToResult(10 + points.get(i + 2));
                                    break;
                                }
                                addScoreToResult(10 + points.get(i + 2) + points.get(i + 3));
                                break;
                        }
                        break;
                    case SPLIT:
                        if (points.size() <= i + 2) {
                            addScoreToResult(10);
                            break;
                        }
                        addScoreToResult(10 + points.get(i + 2));
                        break;
                    case OPEN:
                    default:
                        addScoreToResult(points.get(i) + points.get(i + 1));
                        break;
                }
            }
            frame++;
        }
        return this.result;
    }

    private void addScoreToResult(int newPoints) {
        if (result.size() == 0) {
            result.add(newPoints);
        } else
            result.add(result.get(result.size() - 1) + newPoints);
    }

    private state getFrameState(int a, int b) {
        if (a == 10)
            return state.STRIKE;
        if (a + b == 10)
            return state.SPLIT;

        return state.OPEN;
    }

    // what round/frame it is
    private int getFrame() {
        return (frame / 2) + 1;
    }

    enum state {
        STRIKE, SPLIT, OPEN
    }

}

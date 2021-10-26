/*
 * Copyright 2020 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.lexarcana2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.GenericResult;

/**
 *
 * @author journeyman
 */
public class LexArcana2Results extends GenericResult
{
    private final List<SingleResult<Integer>> results;
    private int poolSize;
    
    public LexArcana2Results(List<SingleResult<Integer>> results)
    {
        List<SingleResult<Integer>> tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
    }
    

    public List<SingleResult<Integer>> getResults()
    {
        return results;
    }
    
    public Integer getTotal()
    {
        int sum = 0;
        for (SingleResult<Integer> r : results)
        {
            sum += r.getValue();
        }
        return sum;
    }

    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        messageBuilder.append("Total: ").append(getTotal()).appendNewLine();
        if (verbose)
        {
            messageBuilder.append("Roll ID: ").append(getUuid()).appendNewLine();
            messageBuilder.append("Results: ").append(" [ ");
            for (SingleResult<Integer> t : getResults())
            {
                messageBuilder.append("( ").append(t.getLabel()).append(" => ");
                messageBuilder.append(t.getValue()).append(") ");
            }
            messageBuilder.append("]\n");
        }
    }

    public int getPoolSize()
    {
        return poolSize;
    }

    public void setPoolSize(int poolSize)
    {
        this.poolSize = poolSize;
    }

}
